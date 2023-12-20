package com.hocafe.config;

import com.hocafe.repository.MemberRepository;
import com.hocafe.repository.MemoryMemberRepository;
import com.hocafe.service.MemberService;
import com.hocafe.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
