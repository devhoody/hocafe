package com.hocafe.service;

import com.hocafe.domain.Member;
import org.apache.ibatis.annotations.Mapper;


public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);

    Member findMemberByName(String name);
}
