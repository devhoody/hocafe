package com.hocafe.controller;

import com.hocafe.domain.Member;
import com.hocafe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("reg")
    public String reg() {
        return "/member/reg";
    }

    @PostMapping("reg")
    public String reg(@RequestParam("name") String name,
                      @RequestParam("age") Long age){

        Member member = Member.builder()
                .name(name)
                .age(age)
                .build();

        memberService.join(member);

        System.out.println("회원가입 완료 이름 = " + member.getName());
        return "redirect:/";
    }
}
