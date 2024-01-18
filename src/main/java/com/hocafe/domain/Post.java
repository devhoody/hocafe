package com.hocafe.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class Post {
    private Long id;
    private String title;
    private String content;
    private Date regDate;
}
