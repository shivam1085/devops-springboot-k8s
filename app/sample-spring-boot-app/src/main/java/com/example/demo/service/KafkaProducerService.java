package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        if (kafkaTemplate == null) {
            System.out.println("KafkaTemplate not configured — skipping send: " + message);
            return;
        }
        kafkaTemplate.send("demo-topic", message);
    }
}
