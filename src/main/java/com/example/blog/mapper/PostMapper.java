package com.example.blog.mapper;

import com.example.blog.vo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> findByPage(@Param("pageNum") Integer pageNum, @Param("getCount") Integer getCount);
    Post findOne(@Param("idx") Integer idx);

    Integer save(@Param("post") Post post);
    Integer update(@Param("post") Post post);

    Integer delete(@Param("idx") Integer idx);

}
