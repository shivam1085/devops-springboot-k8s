package com.example.demo.controller;

import com.example.demo.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    KafkaProducerService producer;

    @GetMapping("/health")
    public String health() {
        return "App running";
    }

    @GetMapping("/publish")
    public String publish() {
        producer.sendMessage("Hello from Spring Boot");
        return "Message sent to Kafka";
    }
}
