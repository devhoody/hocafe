package com.hocafe.repository;

import com.hocafe.domain.Post;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class JpaPostRepository implements PostRepository{

    private final EntityManager em;

    @Autowired
    public JpaPostRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Post save(Post post) {
        em.persist(post);
        return post;
    }

}
