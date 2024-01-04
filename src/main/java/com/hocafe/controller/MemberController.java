package com.hocafe.controller;

import com.hocafe.domain.Member;
import com.hocafe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("list")
    public String list(Model model){
        List<Member> list = memberService.findAll();
        model.addAttribute("list", list);
        System.out.println(list.size());
        return "/member/list";
    }

    @GetMapping("delete")
    public String del() {
        return "/member/delete";
    }

    @PostMapping("delete")
    public String del(@RequestParam("name") String name){
        memberService.delete(name);
        System.out.println("    " + name + "   이(가) 삭제가 완료되었습니다.");
        return "redirect:/";
    }

    @GetMapping("edit")
    public String edit(){
        return "/member/edit";
    }

    @PostMapping("edit")
    public String edit(@RequestParam("beforeName") String beforeName,
                       @RequestParam("afterName") String afterName){
        Member findMember = memberService.findMemberByName(beforeName);
        findMember.setName(afterName);
        memberService.edit(findMember);

        System.out.println("수정완료 이름 : " + afterName);
        return "redirect:/";
    }
}
