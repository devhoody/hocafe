package com.hocafe.repository;

import com.hocafe.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {
    Post save(Post post);

}
