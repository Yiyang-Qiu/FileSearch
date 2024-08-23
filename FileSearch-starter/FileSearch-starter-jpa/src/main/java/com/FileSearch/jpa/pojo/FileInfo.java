package com.FileSearch.jpa.pojo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "tb_fileInfo")
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;     // 文件ID
    private String fileName;   // 文件名
    private String fileType;   // 文件类型
    private long fileSize;     // 文件大小
    private Date createdTime;  // 创建时间
    private Date modifiedTime; // 修改时间
    private String filePath;   // 文件路径
    private boolean isPublic;  // 是否公开
    private List<String> suggestion; ////储存所有相似的词

    public FileInfo(Long fileId, String fileName, String fileType, long fileSize, Date createdTime, Date modifiedTime, String filePath, boolean isPublic) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.filePath = filePath;
        this.isPublic = isPublic;
        this.suggestion = Arrays.asList(this.fileName);
    }
}

