package com.FileSearch.jpa.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RequestParams {
    private String keyword; //关键字
    private Integer page; //搜索到的页数（用来设置分页）
    private Integer size;  //一共搜索到的数量（用来设置分页）
    //private String fileName;   // 文件名
    private Long fileSize;     // 文件大小 （用作条件过滤）
    private Date createdTime;  // 创建时间（用作条件过滤）
    private Date modifiedTime; // 修改时间（用作条件过滤）
    //private String filePath;   // 文件路径
    private Boolean isPublic;  // 是否公开（用作条件过滤）

}
