package com.toxin.donate;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final KafkaProperty kafkaProperty;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(Map.of(
                AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaProperty.getConnection().getBootstrapServers()
        ));
    }

    @Bean
    public NewTopic donationsTopic() {
        return TopicBuilder.name(kafkaProperty.getTopic().getName())
                .partitions(kafkaProperty.getTopic().getPartitions())
                .replicas(kafkaProperty.getTopic().getReplicationFactor())
                .configs(Map.of(
                        TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG,
                        String.valueOf(kafkaProperty.getTopic().getMinInSyncReplicas())
                ))
                .build();
    }

    @Bean
    public ProducerFactory<String, DonateDTO> producerFactory() {
        HashMap<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperty.getConnection().getBootstrapServers());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        config.put(ProducerConfig.ACKS_CONFIG, kafkaProperty.getProducer().getAcks());
        config.put(ProducerConfig.RETRIES_CONFIG, kafkaProperty.getProducer().getRetries());
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, kafkaProperty.getProducer().getIdempotence());
        config.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProperty.getProducer().getName());

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, DonateDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
