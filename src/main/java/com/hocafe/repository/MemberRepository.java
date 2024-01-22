package com.hocafe.repository;

import com.hocafe.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRepository {
    Member save(Member member);

    Member findById(Long memberId);
    Member findByName(String name);

    List<Member> findAll();

    void delete(String name);

    void edit(Long memberId, Member member);
}
