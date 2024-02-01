package com.hocafe.repository;

import com.hocafe.domain.Post;

import java.util.List;

public interface PostRepository {
    Post save(Post post);
    Post findById(Long postId);

    List<Post> findAll();
}
