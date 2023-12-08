package com.hocafe.config;

import com.hocafe.repository.MemberRepository;
import com.hocafe.repository.MemoryMemberRepository;
import com.hocafe.service.MemberService;
import com.hocafe.service.MemberServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


}
