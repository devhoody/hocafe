package com.hocafe.controller;

import com.hocafe.domain.Post;
import com.hocafe.domain.Regions;
import com.hocafe.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller("postController")
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @ModelAttribute("regions")
    public List<Regions> regions(){
        List<Regions> regions = new ArrayList<>();
        regions.add(new Regions(1L,"마포구"));
        regions.add(new Regions(1L,"마포구"));
        return regions;
    }

    @GetMapping("reg")
    public String reg(){

        return "/post/reg";
    }

    @PostMapping("reg")
    public String reg(
            String title,
            String content
    ){
        Post post = Post.builder().title(title).content(content).build();
        postService.reg(post);

        return "redirect:list";
    }
}
