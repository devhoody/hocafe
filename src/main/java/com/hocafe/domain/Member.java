package com.hocafe.domain;

import lombok.*;

@Data
@Builder
public class Member {
    private Long id;
    private String name;
    private Long age;

    public Member() {
    }

    public Member(Long id, String name, Long age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
