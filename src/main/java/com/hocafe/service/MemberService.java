package com.hocafe.service;

import com.hocafe.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface MemberService {
    Member join(Member member);

    Member findMember(Long memberId);

    Member findMemberByName(String name);

    List<Member> findAll();

    void delete(Long memberId);

    void edit(Long memberId, Member member);
}
