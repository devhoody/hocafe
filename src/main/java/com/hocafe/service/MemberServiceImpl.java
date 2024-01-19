package com.hocafe.service;

import com.hocafe.domain.Member;
import com.hocafe.repository.MemberRepository;
import com.hocafe.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    MemoryMemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public Member findMemberByName(String name) {
        return memberRepository.findByName(name);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public void delete(String name) {
        memberRepository.delete(name);
    }

    @Override
    public void edit(Member member) {
        memberRepository.edit(member);
    }
}
