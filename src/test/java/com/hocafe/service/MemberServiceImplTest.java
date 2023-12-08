package com.hocafe.service;

import com.hocafe.config.AppConfig;
import com.hocafe.domain.Member;
import com.hocafe.repository.MemberRepository;
import com.hocafe.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {

    MemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberRepository = appConfig.memberRepository();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA");
        //when
        memberService.join(member);

        //then
        Member newOne = memberService.findMember(1L);
        Assertions.assertThat(newOne.getName()).isEqualTo("memberA");
    }

    @Test
    void findMember() {

    }
}