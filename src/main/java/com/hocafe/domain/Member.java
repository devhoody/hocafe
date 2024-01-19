package com.hocafe.domain;

import lombok.*;

@Data
@Builder
public class Member {
    private Long id;
    private String memberName;
    private Long age;

    public Member() {
    }

    public Member(Long id, String name, Long age) {
        this.id = id;
        this.memberName = name;
        this.age = age;
    }
}
