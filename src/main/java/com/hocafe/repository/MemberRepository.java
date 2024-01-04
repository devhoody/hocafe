package com.hocafe.repository;

import com.hocafe.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
    Member findByName(String name);

}
