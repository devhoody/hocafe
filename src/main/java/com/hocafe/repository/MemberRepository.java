package com.hocafe.repository;

import com.hocafe.domain.Member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);

}
