package com.FileSearch.jpa.controller;

import com.FileSearch.jpa.elasticsearch.ElasticSearchService;
import com.FileSearch.jpa.elasticsearch.SearchService;
import com.FileSearch.jpa.pojo.FileInfo;
import com.FileSearch.jpa.pojo.PageResult;
import com.FileSearch.jpa.pojo.RequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class FileSearchController {
    @Autowired
    private ElasticSearchService elasticSearchService;

    @Autowired
    private SearchService searchService;

    //添加索引库
    @PostMapping("/index")
    public ResponseEntity<String> createIndex() {
        try {
            elasticSearchService.createIndex();
            return ResponseEntity.ok("Index created successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error creating index: " + e.getMessage());
        }
    }

    //删除索引库
    @PostMapping("/deleteIndex")
    public ResponseEntity<String> deleteIndex() {
        try {
            elasticSearchService.deleteIndex();
            return ResponseEntity.ok("Index deleted successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error deleting index: " + e.getMessage());
        }
    }

    //索引库中添加doc
    @PostMapping("/addDoc")
    public ResponseEntity<String> createFileInfo(@RequestBody FileInfo fileInfo) {
        try {
            elasticSearchService.saveFileInfo(fileInfo);
            return ResponseEntity.ok("File information saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving file information: " + e.getMessage());
        }
    }

    //索引库中根据id查找doc
    @GetMapping("/file/{id}")
    public ResponseEntity<FileInfo> getFileInfo(@PathVariable("id") long id) {
        try {
            FileInfo fileInfo = elasticSearchService.getFileInfoById(id);
            return ResponseEntity.ok(fileInfo);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    //索引库中根据id更新doc
    @PutMapping("/file/{id}")
    public ResponseEntity<String> updateFileInfo(@PathVariable("id") long id, @RequestBody FileInfo fileInfo) {
        try {
            elasticSearchService.updateFileInfo(id, fileInfo);
            return ResponseEntity.ok("File information updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating file information: " + e.getMessage());
        }
    }

    //索引库中根据id删除doc
    @DeleteMapping("/file/{id}")
    public ResponseEntity<String> deleteFileInfo(@PathVariable("id") long id) {
        try {
            elasticSearchService.deleteFileInfo(id);
            return ResponseEntity.ok("File information deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting file information: " + e.getMessage());
        }
    }

    //根据用户输入的关键词 在索引库中搜索（带有关键词的）文件
    @PostMapping("/list")
    public PageResult search(@RequestBody RequestParams params){
        return searchService.search(params);
    }

    //用户输入关键词时弹出来的自动补全
    @GetMapping("suggestion")
    public List<String> getSuggestion(@RequestParam("keyword") String prefix){
            return searchService.getSuggestion(prefix);
    }
}
