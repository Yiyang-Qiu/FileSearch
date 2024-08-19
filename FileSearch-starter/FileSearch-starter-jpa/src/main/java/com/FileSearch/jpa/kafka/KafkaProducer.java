package com.FileSearch.jpa.kafka;

import com.FileSearch.jpa.pojo.FileInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC = "file_info_topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(FileInfo fileInfo) {
        try {
            String message = objectMapper.writeValueAsString(fileInfo);
            kafkaTemplate.send(TOPIC, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
