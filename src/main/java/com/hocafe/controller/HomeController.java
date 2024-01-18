package com.hocafe.controller;

import com.hocafe.domain.Post;
import com.hocafe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("HomeController")
@RequestMapping("/")
public class HomeController {

    private final PostService postService;

    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @PostMapping("index")
    public String reg(
            String title,
            String content
    ){
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();

        Post reg = postService.reg(post);
        System.out.println(reg);

        System.out.println("등록 활성화");
        return "redirect:/index";
    }


}
