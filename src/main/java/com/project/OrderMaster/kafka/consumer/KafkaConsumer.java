package com.project.OrderMaster.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.OrderMaster.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "orders", groupId = "group_id")
    public void listen(String message) {
        logger.info("Received message: " + message);
        try {
            // Deserializar a mensagem para um objeto Product
            Product product = objectMapper.readValue(message, Product.class);
            // Logar as informações do produto
            logger.info("Product registered successfully: " + product);
        } catch (Exception e) {
            logger.error("Failed to process message: " + message, e);
        }
    }
}