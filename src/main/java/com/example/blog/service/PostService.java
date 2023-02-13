package com.example.blog.service;

import com.example.blog.mapper.PostMapper;
import com.example.blog.vo.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostMapper postMapper;

    public PostService(PostMapper postMapper){
        this.postMapper = postMapper;
    }
    public List<Post> getPostsByPage(Integer page) {
        return postMapper.findByPage(page,2);
    }

    public Post getPostById(Integer idx){
        return postMapper.findOne(idx);
    }

    public boolean savePost(Post post){
        Integer result = postMapper.save(post);
        return  result == 1;
    }

    public boolean updatePost(Post post){
        Integer result = postMapper.update(post);
        return  result == 1;
    }

    public boolean deletePost(Integer idx){

        Integer result = postMapper.delete(idx);
        return result == 1;

    }
}
