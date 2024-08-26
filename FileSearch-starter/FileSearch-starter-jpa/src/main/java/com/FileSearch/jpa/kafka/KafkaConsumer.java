package com.FileSearch.jpa.kafka;

import com.FileSearch.jpa.elasticsearch.ElasticSearchService;
import com.FileSearch.jpa.pojo.FileInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "file_info_topic", groupId = "myGroup")
    public void consume(FileInfo fileInfo) {
        LOGGER.info(String.format("message received -> %s", fileInfo.toString()));
        //ObjectMapper objectMapper = new ObjectMapper();
        try{
            //FileInfo fileInfo = objectMapper.readValue(message, FileInfo.class);
            //elsaticsearch 新建data
            elasticSearchService.saveFileInfo(fileInfo);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
