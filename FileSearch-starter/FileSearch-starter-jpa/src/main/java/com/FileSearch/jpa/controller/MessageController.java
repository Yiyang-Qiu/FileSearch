package com.FileSearch.jpa.controller;

import com.FileSearch.jpa.kafka.KafkaProducer;
import com.FileSearch.jpa.pojo.FileInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

    private KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody FileInfo fileInfo) {
        kafkaProducer.sendMessage(fileInfo);
        return ResponseEntity.ok("message sent to topic");
    }
}
