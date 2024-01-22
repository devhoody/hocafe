package com.hocafe.repository;

import com.hocafe.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
    }

//    @Test
//    void save() {
//        // given
//        Member member = new Member(1L, "memberA", 44L);
//        //when
//        memberRepository.save(member);
//        //then
//        Member newMember = memberRepository.findById(1L);
//        Assertions.assertThat(member.getName()).isEqualTo(newMember.getName());
//    }

    @Test
    void findById() {
        // given
        Member member = new Member(1L, "memberA", 44L);

        //when
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId());
        //then
        Long id = findMember.getId();
        assertThat(id).isEqualTo(member.getId());
    }

    @Test
    void 회원탈퇴(){
        //given
        Member memberA = new Member(1L, "memberA", 40L);
        Member memberB = new Member(2L, "memberB", 30L);
        memberRepository.save(memberA);
        memberRepository.save(memberB);
        //when
        memberRepository.delete(memberA.getId());

        //then
        assertThat(memberRepository.findAll().size()).isEqualTo(1);

    }
}