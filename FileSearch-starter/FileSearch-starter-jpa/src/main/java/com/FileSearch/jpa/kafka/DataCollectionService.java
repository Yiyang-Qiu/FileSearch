//package com.FileSearch.jpa.kafka;
//
//import com.FileSearch.jpa.pojo.FileInfo;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.sql.Date;
//
//@Service
//public class DataCollectionService {
//
//    @Autowired
//    private KafkaProducer kafkaProducer;
//
//    public void collectAndSendData(String title, String content, Date time){
//        ObjectMapper objectMapper = new ObjectMapper();
//        FileInfo fileInfo = new FileInfo();
//        try{
//            String jsonMessage = objectMapper.writeValueAsString(fileInfo);
//        }catch (JsonProcessingException e){
//            e.printStackTrace();
//        }
//    }
//}
