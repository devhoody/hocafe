package com.hocafe.repository;

import com.hocafe.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Member result = memberRepository.save(member);

        //then
        Assertions.assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        //when
        memberRepository.save(member1);
        Member result = memberRepository.findByName(member1.getName()).get();

        //then
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    void findById() {
        //given
        Member member = new Member();
        member.setName("spring");
        memberRepository.save(member);


        //when
        Member result = memberRepository.findById(member.getId()).get();
        //then
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        //when
        List<Member> list = memberRepository.findAll();

        //then
        Assertions.assertThat(list.size()).isEqualTo(2);

    }
}