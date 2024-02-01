package com.hocafe.controller;

import com.hocafe.domain.Member;
import com.hocafe.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostConstruct
    public void init() {
        memberService.join(new Member(1L, "memberA", 10L));
        memberService.join(new Member(2L, "memberB", 20L));
    }

    @GetMapping
    public String index(Model model) {
        List<Member> list = memberService.findAll();
        model.addAttribute("members", list);
        return "/member/list";
    }

    @GetMapping("reg")
    public String reg(Model model) {
        model.addAttribute("member", new Member());
        return "/member/reg";
    }

    @PostMapping("reg")
    public String addMember(@ModelAttribute Member member, RedirectAttributes redirectAttributes, Model model) {

        Map<String, String> errors = new HashMap<>();

        if (!StringUtils.hasText(member.getMemberName())) {
            errors.put("memberName","이름은 필수입니다.");
        }

        //나이 검증
        if (member.getAge() == null) {
            errors.put("age", "나이 입력은 필수입니다.");
        }

        //검증 실패시 리턴하기
        if(!errors.isEmpty()){
            model.addAttribute("errors", errors);
            return "/member/reg";
        }

        Member savedMember = memberService.join(member);

        log.info("PolicyAgree= {}", member.getPolicyAgree());
        model.addAttribute("member", savedMember);
        redirectAttributes.addAttribute("memberId", savedMember.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/members/{memberId}";
    }

    @GetMapping("{memberId}")
    public String member(@PathVariable(name = "memberId") Long memberId, Model model){
        log.info("세부사항 멤버 ID = {}", memberId);
        Member findMember = memberService.findMember(memberId);
        model.addAttribute("member", findMember);
        return "/member/member";
    }

    @GetMapping("list")
    public String list(Model model) {
        List<Member> list = memberService.findAll();
        model.addAttribute("list", list);
        log.info("총 회원 수 : {}", list.size());
        return "/member/list";
    }

    @GetMapping("{memberId}/edit")
    public String edit(@PathVariable(name = "memberId") Long memberId, Model model) {
        log.info("memberId = {}", memberId);
        Member member = memberService.findMember(memberId);
        log.info("findMemberId = {}", member.getId());
        model.addAttribute("member", member);
        return "member/edit";
    }

    @PostMapping("{memberId}/edit")
    public String edit(@PathVariable(name = "memberId") Long memberId, @ModelAttribute Member member) {
        memberService.edit(memberId, member);
        return "redirect:/members/{memberId}";
    }

    @DeleteMapping("{memberId}")
    public String del(@PathVariable(name = "memberId") Long memberId) {
        log.info("삭제되는 회원 이름 : {}", memberService.findMember(memberId).getMemberName());
        memberService.delete(memberId);
        return "redirect:/members";
    }
}
