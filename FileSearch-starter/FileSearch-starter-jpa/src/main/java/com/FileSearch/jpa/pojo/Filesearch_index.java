//package com.FileSearch.jpa.pojo;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.sql.Date;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "filesearch_index")
//public class Filesearch_index {
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String recordId;  // 记录ID
//
//    @Column(name = "file_id")
//    private String fileId;    // 文件ID
//
//    @Column(name = "user_id")
//    private String userId;    // 用户ID
//
//    @Column(name = "operation_type")
//    private String operationType; // 操作类型
//
//    @Column(name = "operation_time")
//    private Date operationTime;   // 操作时间
//}
