package com.example.blog.mapper;

import com.example.blog.vo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> findByPage(@Param("post_idx") Integer post_idx,
                             @Param("pageNum") Integer pageNum,
                             @Param("getCount") Integer getCount);

    Integer save(@Param("comment") Comment comment);
    Integer update(@Param("comment") Comment comment);

    Integer delete(@Param("idx") Integer idx);

    Integer count(@Param("post_idx") Integer post_idx);
}
