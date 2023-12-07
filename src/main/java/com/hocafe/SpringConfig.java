package com.hocafe;

import com.hocafe.aop.TimeTraceAop;
import com.hocafe.domain.Member;
import com.hocafe.repository.JpaMemberRepository;
import com.hocafe.repository.MemberRepository;
import com.hocafe.service.MemberServiceImp;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberServiceImp memberServiceImp(){
        return new MemberServiceImp(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
