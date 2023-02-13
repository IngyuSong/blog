package com.example.blog.service;

import com.example.blog.mapper.CommentMapper;
import com.example.blog.vo.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper){
        this.commentMapper = commentMapper;
    }

    public List<Comment> getCommentsByPage(Integer post_idx, Integer page){
        return commentMapper.findByPage(post_idx, page,2);
    }


    public Integer countComments(Integer post_idx){
        return commentMapper.count(post_idx);
    }

    public boolean saveComment(Comment comment){
        Integer result = commentMapper.save(comment);
        return  result == 1;
    }

    public boolean updateComment(Comment comment){
        Integer result = commentMapper.update(comment);
        return result == 1;
    }

    public boolean deleteComment(Integer idx){
        Integer result = commentMapper.delete(idx);
        return result == 1;
    }
}
