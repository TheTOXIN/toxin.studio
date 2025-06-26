package com.toxin.donate;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DonateProducer {

    private final KafkaTemplate<String, DonateDTO> kafkaTemplate;
    private final KafkaProperty kafkaProperty;

    public void sendDonate(DonateDTO donate) {
        kafkaTemplate.send(new ProducerRecord<>(
                kafkaProperty.getTopic().getName(),
                donate.id().toString(),
                donate
        )).thenAccept(result -> {
            System.out.println("SENT MESSAGE SUCCESS" +
                    " KEY: " + result.getProducerRecord().key() +
                    " VALUE: " + result.getProducerRecord().value() +
                    " PARTITION: " + result.getRecordMetadata().partition()
            );
        }).exceptionally(ex -> {
            System.err.println("SENT MESSAGE ERROR: " + ex.getMessage());
            return null;
        });
    }
}
