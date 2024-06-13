package com.project.OrderMaster.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.OrderMaster.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final String TOPIC = "orders";
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Product product) {
        try {
            String message = objectMapper.writeValueAsString(product);
            kafkaTemplate.send(TOPIC, message);
            logger.info("Sent message: " + message);
        } catch (Exception e) {
            logger.error("Failed to send message: " + product, e);
        }
    }
}
