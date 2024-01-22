package com.hocafe.controller;

import com.hocafe.domain.Member;
import com.hocafe.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostConstruct
    public void init(){
        memberService.join(new Member(1L,"memberA",10L));
        memberService.join(new Member(2L,"memberB",20L));
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
    public String reg(@ModelAttribute Member member, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if(!StringUtils.hasText(member.getMemberName())){
//            bindingResult.addError(new FieldError("member", "memberName", member.getMemberName(), false, null, null,  "이름은 필수입니다."));
//            bindingResult.addError(new FieldError("member", "memberName", member.getMemberName(), false, new String[]{"required.member.memberName"}, null,  "이름은 필수입니다."));
            bindingResult.rejectValue("memberName","required");
        }
        //나이
        if(member.getAge() == null){
//            bindingResult.addError(new FieldError("member", "age", member.getAge(), false, new String[]{"required.member.age"}, null, "나이는 필수입니다."));
            bindingResult.rejectValue("age","required");
        }

        log.info("objectName={}", bindingResult.getObjectName());
        log.info("target={}", bindingResult.getTarget());

        //검증 실패시 리턴하기
        if(bindingResult.hasErrors()){
            log.info("BidingResult ={}", bindingResult);
            return "/member/reg";
        }

        Member savedMember = memberService.join(member);
        log.info("memberId ={}", savedMember.getId());

        model.addAttribute("member", savedMember);
        redirectAttributes.addAttribute("memberId", savedMember.getId());
        redirectAttributes.addAttribute("status", true);
        log.info("회원가입 완료 이름 : {}", member.getMemberName());
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
