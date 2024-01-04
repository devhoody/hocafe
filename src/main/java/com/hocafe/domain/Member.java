package com.hocafe.domain;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class Member {
    private Long id;
    private String name;
    private Long age;

}
