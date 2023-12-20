package com.hocafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("HomeController")
@RequestMapping("/")
public class HomeController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @PostMapping("index")
    public String reg(
            String title,
            String content
    ){
        System.out.println("등록 활성화");
        return "redirect:/index";
    }


}
