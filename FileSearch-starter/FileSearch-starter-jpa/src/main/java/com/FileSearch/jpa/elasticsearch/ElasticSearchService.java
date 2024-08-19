package com.FileSearch.jpa.elasticsearch;

import com.FileSearch.jpa.pojo.FileInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
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

import static com.FileSearch.jpa.elasticsearch.FileSearchConstant.mapping;

@Service
public class ElasticSearchService {

    private RestHighLevelClient client;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String INDEX = "file_info_index";

    //设置rest client
    public void setClient() {
        this.client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://127.0.0.1:9200")));
    }

    //创建索引库
    public void createIndex() throws IOException {
        //创建request对象
        CreateIndexRequest request = new CreateIndexRequest(INDEX);
        //准备请求参数：dsl语句
        request.source(mapping, XContentType.JSON);
        //发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
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
    public void deleteFileInfo(Long fileId) throws Exception {
        //删除请求
        DeleteRequest request = new DeleteRequest(INDEX, fileId.toString());
        //发送
        client.delete(request, RequestOptions.DEFAULT);
    }

    //关闭rest client
    public void closeClient() throws IOException {
        this.client.close();
    }


}
