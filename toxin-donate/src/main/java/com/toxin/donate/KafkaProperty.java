package com.toxin.donate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperty {

    @NestedConfigurationProperty
    private KafkaPropertyConnection connection;

    @NestedConfigurationProperty
    private KafkaPropertyProducer producer;

    @NestedConfigurationProperty
    private KafkaPropertyTopic topic;

    @Getter
    @Setter
    public static class KafkaPropertyConnection {
        private String bootstrapServers;
    }

    @Getter
    @Setter
    public static class KafkaPropertyProducer {
        private String name;
        private String acks;
        private int retries;
        private String idempotence;
    }

    @Getter
    @Setter
    public static class KafkaPropertyTopic {
        private String name;
        private int partitions;
        private int replicationFactor;
        private int minInSyncReplicas;
    }
}
