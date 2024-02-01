package com.hocafe.service;

import com.hocafe.domain.Post;
import com.hocafe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post reg(Post post) {
        postRepository.save(post);
        return post;
    }

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

}
