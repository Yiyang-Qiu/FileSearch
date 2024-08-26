package com.FileSearch.jpa.elasticsearch;

import com.FileSearch.jpa.pojo.FileInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;

import static com.FileSearch.jpa.elasticsearch.FileSearchConstant.MAPPING;

@Service
public class ElasticSearchService {

    @Autowired
    private ObjectMapper objectMapper;

    private static final String INDEX = "file_info_index";

    //设置并注入rest client
    @Autowired
    private RestHighLevelClient client;

    //创建索引库
    public void createIndex() throws IOException {
        try {
            System.out.println("Creating index...1");
            //搭建请求
            CreateIndexRequest request = new CreateIndexRequest(INDEX);

            System.out.println("Creating index...2");
            //映射dsl语句
            request.source(MAPPING, XContentType.JSON);

            //print statement debugging
            System.out.println("Mapping JSON: " + MAPPING);

            System.out.println("Creating index...3");
            //发送请求
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

            //debug
            if (createIndexResponse.isAcknowledged()) {
                System.out.println("Index created successfully.");
            } else {
                System.out.println("Index creation not acknowledged.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating index: " + e.getMessage());
            throw e;
        }
    }

    //删除索引库
    public void deleteIndex() throws IOException {
        //创建request对象
        DeleteIndexRequest request = new DeleteIndexRequest(INDEX);
        //发送删除请求
        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    //添加文档/记录
    public void saveFileInfo(FileInfo fileInfo) throws IOException {
        //创建request对象
        IndexRequest request = new IndexRequest(INDEX).id(fileInfo.getFileId().toString());
        //准备Json文档
        request.source(objectMapper.writeValueAsString(fileInfo), XContentType.JSON);
        //发送请求
        client.index(request, RequestOptions.DEFAULT);
    }


    //获取文档/记录
    public FileInfo getFileInfoById(Long fileId) throws Exception {
        //创建request对象
        GetRequest request = new GetRequest(INDEX, fileId.toString());
        //发送请求 得到响应
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        //返回取得结果
        return objectMapper.readValue(response.getSourceAsString(), FileInfo.class);
    }

    //更新文档/记录
    public void updateFileInfo(Long fileId, FileInfo fileInfo) throws Exception {
        //更新请求
        UpdateRequest request = new UpdateRequest(INDEX, fileId.toString())
                .doc(objectMapper.writeValueAsString(fileInfo), XContentType.JSON);
        //发送请求
        UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
    }

    //删除文档/记录
    public void deleteFileInfo(long fileId) throws Exception {
        //删除请求
        DeleteRequest request = new DeleteRequest(INDEX, fileId+"");
        //发送
        client.delete(request, RequestOptions.DEFAULT);
    }

    //关闭rest client
    public void close() throws IOException {
        this.client.close();
    }


}
