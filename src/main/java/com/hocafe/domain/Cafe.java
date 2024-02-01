package com.hocafe.domain;

import lombok.Data;

import java.util.List;

@Data
public class Cafe {
    private Long id;
    private String cafeName;
    private String oepnTime;
    private String cloaseTime;
    private String address;
    private String phone;
    private String mainMenu;
    private UploadFile mainImage;
    private List<UploadFile> basicImages;
}
