package com.hocafe.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CafeForm {
    private Long id;
    private String cafeName; //카페 이름
    private String address; //카페 주소
    private MultipartFile mainImageFile; // 카페 메인사진
    private List<MultipartFile> imageFiles; // 카페 이미지사진
}
