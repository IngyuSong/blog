package com.example.blog.service;

import com.example.blog.mapper.PostMapper;
import com.example.blog.vo.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    public List<Post> getPostByPage(Integer page, Integer size) {
        return postMapper.findByPage(size, (page-1) * size);
    }

    public Post getPostById(Integer id) {
        return postMapper.findOne(id);
    }

    public boolean savePost(Post post) {
        Integer result = postMapper.save(post);
        return result == 1;
    }

    public boolean updatePost(Post post) {
        Integer result = postMapper.update(post);
        return result == 1;
    }

    public boolean deletePost (Integer postId) {
        Integer result = postMapper.delete(postId);
        return result == 1;
    }
}