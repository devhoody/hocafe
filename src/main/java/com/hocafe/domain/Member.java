package com.hocafe.domain;

import lombok.*;

@Data
public class Member {
    private Long id;
    private String memberName;
    private Long age;
    private Boolean policyAgree; //선택약관 동의여부

    public Member() {
    }

    public Member(Long id, String name, Long age) {
        this.id = id;
        this.memberName = name;
        this.age = age;
    }
}
