package com.hocafe.domain;

import lombok.Data;

import java.util.List;

@Data
public class Cafe {
    private Long id;
    private String cafeName; //카페 이름
    private String address; //카페 주소
    private UploadFile mainImageFile; // 카페 메인사진
    private List<UploadFile> imageFiles; // 카페 이미지사진
}
