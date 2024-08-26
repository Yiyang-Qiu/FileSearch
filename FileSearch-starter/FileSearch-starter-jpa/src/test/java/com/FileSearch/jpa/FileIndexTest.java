package com.FileSearch.jpa;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.FileSearch.jpa.elasticsearch.FileSearchConstant.mapping;

@SpringBootTest
public class FileIndexTest {

    private static final String INDEX = "file_info_index";

    @Autowired
    private RestHighLevelClient  client;


    @Test
    public void testFileIndex() throws IOException {
        //创建request对象
        CreateIndexRequest request = new CreateIndexRequest(INDEX);
        //准备请求参数：dsl语句
        request.source(mapping, XContentType.JSON);
        //发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }


}
