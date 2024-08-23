package com.FileSearch.jpa.controller;

import com.FileSearch.jpa.elasticsearch.ElasticSearchService;
import com.FileSearch.jpa.elasticsearch.SearchService;
import com.FileSearch.jpa.pojo.PageResult;
import com.FileSearch.jpa.pojo.RequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/file_search")
public class FileSearchController {
    @Autowired
    private ElasticSearchService elasticSearchService;

    @Autowired
    private SearchService searchService;

    @PostMapping("/list")
    public PageResult search(@RequestParam RequestParams params){
        return searchService.search(params);
    }

    @GetMapping("suggestion")
    public List<String> getSuggestion(@RequestParam("keyWor d") String prefix){
            return searchService.getSuggestion(prefix);
    }
}
