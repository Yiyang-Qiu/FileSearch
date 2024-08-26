package com.FileSearch.jpa.kafka;

import com.FileSearch.jpa.kafka.kafkaTopic.KafkaTopicConfig;
import com.FileSearch.jpa.pojo.FileInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.config.TopicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, FileInfo> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, FileInfo> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(FileInfo fileInfo) {
        try {
            Message<FileInfo> message = MessageBuilder.withPayload(fileInfo)
                    .setHeader(KafkaHeaders.TOPIC, "file_info_topic")
                    .build();
            //String message = objectMapper.writeValueAsString(fileInfo);
            LOGGER.info(String.format("Message sent -> %s", fileInfo.toString()));
            //kafkaTemplate.send("file_info_topic", message);
            kafkaTemplate.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
