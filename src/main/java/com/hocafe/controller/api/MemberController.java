package com.hocafe.controller.api;

import com.hocafe.domain.Member;
import com.hocafe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("apiMemberController")
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService service;

    //이름 중복유무 확인
    @GetMapping("edit")
    public boolean edit(
            @RequestParam(name= "name") String name
    ) {
        System.out.println(name);
        Member findMember = service.findMemberByName(name);
        System.out.println(findMember);
        if(findMember == null) {
            return false;
        }
        return true;
    }

    @PutMapping("edit")
    public boolean edit(Member member){
        System.out.println("변경할 member 이름 : " + member.getMemberName());
        service.edit(member.getId(), member);
        return true;
    }
}
