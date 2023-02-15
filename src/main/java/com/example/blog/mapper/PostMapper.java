package com.example.blog.mapper;

import com.example.blog.vo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> findByPage(@Param("limit") Integer limit, @Param("offset") Integer offset);
    Post findOne(@Param("id") Integer id);
    Integer save(@Param("post") Post post);
    Integer update(@Param("post") Post post);
    Integer delete(@Param("id") Integer postId);

}
