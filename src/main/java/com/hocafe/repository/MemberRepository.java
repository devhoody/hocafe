package com.hocafe.repository;

import com.hocafe.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByName(String name);
    Optional<Member> findById(Long memberId);
    List<Member> findAll();

    void clearStore();
}
