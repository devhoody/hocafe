package com.hocafe.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
public class Post {
    private Long id;
    private String title;
    private String content;
    private Date regDate;
    private UploadFile attachFile;
    private List<UploadFile> imageFiles;
}
