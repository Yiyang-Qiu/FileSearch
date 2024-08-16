package com.FileSearch.jpa.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class DataCollectionService {

    @Autowired
    private KafkaProducer kafkaProducer;

    public void collectAndSendData(String title, String content, Date time){
        ObjectMapper objectMapper = new ObjectMapper();
        DataMessage dataMessage = new DataMessage(title, content, time);
        try{
            String jsonMessage = objectMapper.writeValueAsString(dataMessage);
            kafkaProducer.sendMessage(jsonMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
