package com.hocafe.service;

import com.hocafe.domain.Post;

import java.util.List;

public interface PostService {
    Post reg(Post post);

    Post findById(Long postId);

    List<Post> findAll();
}
