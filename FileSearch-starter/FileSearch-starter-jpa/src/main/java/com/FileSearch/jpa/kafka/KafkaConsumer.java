package com.FileSearch.jpa.kafka;

import com.FileSearch.jpa.elasticsearch.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private ElasticSearchService elasticSearchService;



}
