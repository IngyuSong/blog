package com.example.blog.mapper;

import com.example.blog.vo.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> getAllPosts();
}
