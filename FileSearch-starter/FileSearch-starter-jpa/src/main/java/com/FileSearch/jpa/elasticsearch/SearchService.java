package com.FileSearch.jpa.elasticsearch;

import com.FileSearch.jpa.pojo.FileInfo;
import com.FileSearch.jpa.pojo.PageResult;
import com.FileSearch.jpa.pojo.RequestParams;
import com.alibaba.fastjson.JSON;
import org.apache.lucene.search.BooleanQuery;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    private ElasticSearchService elasticSearchService;

    @Autowired
    private RestHighLevelClient client;

    public List<String> getSuggestion(String prefix){
        try {
            //创建搜索请求
            SearchRequest request = new SearchRequest("file_info_index");
            //准备DSL
            //以关键词的第一个字符开始搜索，搜索条数为10（最多）
            request.source().suggest(new SuggestBuilder().addSuggestion("suggestions",
                    SuggestBuilders.completionSuggestion("suggestion")
                            .prefix(prefix)
                            .skipDuplicates(true)
                            .size(10)
                            ));
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            Suggest suggest = response.getSuggest();
            //根据名称获取补全结果
            CompletionSuggestion suggestion = suggest.getSuggestion("suggestions");
            //获取所有的options
            List<CompletionSuggestion.Entry.Option> options = suggestion.getOptions();

            List<String> list = new ArrayList<>(options.size());
            //获取option 遍历
            for(Suggest.Suggestion.Entry.Option option : options){
                //获取option中的text
                String text = option.getText().toString();
                list.add(text);
            }

            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public PageResult search(RequestParams params){
        try {
            //创建搜索请求
            SearchRequest request = new SearchRequest("file_info_index");
            //准备DSL
            //query
            //boolean query
            BoolQueryBuilder booleanQuery = QueryBuilders.boolQuery();

            String keyword = params.getKeyword();
            //判断搜索结果是否为空
            if(keyword == null || keyword.isEmpty()){
                booleanQuery.must(QueryBuilders.matchAllQuery());
            }else{
                booleanQuery.must(QueryBuilders.matchQuery("all", params.getKeyword()));
            }


            //条件过滤 文件大小
            if(params.getFileSize() != null && params.getFileSize() > 0){
                booleanQuery.filter(QueryBuilders.rangeQuery("fileSize").gte(params.getFileSize()));
            }
            //条件过滤 文件创建日期之后
            if(params.getCreatedTime() != null){
                booleanQuery.filter(QueryBuilders.rangeQuery("createdTime").gte(params.getCreatedTime()));
            }
            //条件过滤 文件修改日期之后
            if(params.getModifiedTime() != null){
                booleanQuery.filter(QueryBuilders.rangeQuery("modifiedTime").gte(params.getModifiedTime()));
            }
            //条件过滤 文件是否公开
            if(params.getIsPublic() != null){
                booleanQuery.filter(QueryBuilders.matchQuery("isPublic", params.getIsPublic()));
            }

            //算分控制
            FunctionScoreQueryBuilder functionScoreQuery =
                    QueryBuilders.functionScoreQuery(
                            //原始查询，相关性算分
                            booleanQuery,
                            //function score
                            new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                                    new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                            QueryBuilders.termQuery("isPublic", true),
                                            ScoreFunctionBuilders.weightFactorFunction(10)
                                    )
                            });

            request.source().query(functionScoreQuery);

            //分页
            int page = params.getPage();
            int size = params.getSize();
            request.source().from((page-1) * size).size(size);

            //发送请求
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            return handleResponse(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PageResult handleResponse(SearchResponse response){
        SearchHits hits = response.getHits();
        //总条数
        long total = hits.getTotalHits().value;

        SearchHit[] searchHits = hits.getHits();

        List<FileInfo> fileInfos = new ArrayList<>();
        for(SearchHit hit : searchHits){
            String Json = hit.getSourceAsString();
            //反序列化
            FileInfo fileInfo = JSON.parseObject(Json, FileInfo.class);
            fileInfos.add(fileInfo);
        }
        return new PageResult(total, fileInfos);
    }

}
