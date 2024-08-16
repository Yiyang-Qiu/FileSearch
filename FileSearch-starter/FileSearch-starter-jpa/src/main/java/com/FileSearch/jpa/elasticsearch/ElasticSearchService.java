package com.FileSearch.jpa.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.FileSearch.jpa.elasticsearch.fileSearchConstant.mapping;

@Service
public class ElasticSearchService {

    private RestHighLevelClient client;

    //设置rest client
    void setClient() {
        this.client = new RestHighLevelClient(RestClient.builder(HttpHost.create("http://127.0.0.1:9200")));
    }

    //创建索引库
    void createIndex() throws IOException {
        //创建request对象
        CreateIndexRequest request = new CreateIndexRequest("filesearch_index");
        //准备请求参数：dsl语句
        request.source(mapping, XContentType.JSON);
        //发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    //删除索引库
    void deleteIndex() throws IOException {
        //创建request对象
        DeleteIndexRequest request = new DeleteIndexRequest("filesearch_index");
        //发送删除请求
        client.indices().delete(request, RequestOptions.DEFAULT);
    }


    //添加文档/记录
    void addDocument() throws IOException {
        //创建request对象
        IndexRequest request = new IndexRequest("filesearch_index").id("");
        //准备Json文档
        request.source("", XContentType.JSON);
        //发送请求
        client.index(request, RequestOptions.DEFAULT);
    }

    //获取文档/记录
    void getDocumentById(String id) throws IOException {
        //创建request对象
        GetRequest request = new GetRequest("filesearch_index").id(id);
        //发送请求 得到响应
        GetResponse response = client.get(request, RequestOptions.DEFAULT);

        //解析响应结果
        String json = response.getSourceAsString();
        //转型

        //返回取得结果
        //return
    }

    //更新文档/记录
    void updateDocumentById(String id) throws IOException {
        UpdateRequest request = new UpdateRequest("filesearch_index", id);
        //更新请求
        request.doc(

        );

        client.update(request, RequestOptions.DEFAULT);
    }

    //删除文档/记录
    void deleteDocumentById(String id) throws IOException {
        DeleteRequest request = new DeleteRequest("filesearch_index", id);
        client.delete(request, RequestOptions.DEFAULT);
    }


    //关闭rest client
    void closeClient() throws IOException {
        this.client.close();
    }


}
