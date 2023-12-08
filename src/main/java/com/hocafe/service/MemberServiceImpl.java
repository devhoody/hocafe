package com.hocafe.service;

import com.hocafe.domain.Member;
import com.hocafe.repository.MemberRepository;
import com.hocafe.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService{

    MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
