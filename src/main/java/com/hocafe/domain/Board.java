package com.hocafe.domain;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private Long id;
    private String title;
    private String content;
    private Date regDate;
}
