package com.hocafe.controller;

import com.hocafe.domain.Member;
import com.hocafe.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String index() {
        return "/member/index";
    }

    @GetMapping("reg")
    public String reg(Model model) {
        model.addAttribute("member", new Member());
        return "/member/reg";
    }

    @PostMapping("reg")
    public String reg(@ModelAttribute Member member, Model model) {
        memberService.join(member);

        List<Member> list = memberService.findAll();
        model.addAttribute("list", list);
        log.info("회원가입 완료 이름 : {}", member.getName());
        return "redirect:/member";
    }

    @GetMapping("list")
    public String list(Model model) {
        List<Member> list = memberService.findAll();
        model.addAttribute("list", list);
        log.info("총 회원 수 : {}", list.size());
        return "/member/list";
    }

    @GetMapping("delete")
    public String del() {
        return "/member/delete";
    }

    @PostMapping("delete")
    public String del(@RequestParam("name") String name) {
        memberService.delete(name);
        log.info("----- {} (이)가 정상적으로 탈퇴되었습니다.)", name);
        return "redirect:/member";
    }

    @GetMapping("edit")
    public String edit() {
        return "/member/edit";
    }

    @PostMapping("edit")
    public String edit(@RequestParam("beforeName") String beforeName,
                       @RequestParam("afterName") String afterName) {
        Member findMember = memberService.findMemberByName(beforeName);
        findMember.setName(afterName);
        memberService.edit(findMember);

        log.info("수정 완료 이름 : {}", afterName);
        return "redirect:/member";
    }
}
