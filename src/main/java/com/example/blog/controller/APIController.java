package com.example.blog.controller;

import com.example.blog.dto.CommentRequestDto;
import com.example.blog.dto.CommentResponseDto;
import com.example.blog.dto.PostRequestDto;
import com.example.blog.dto.PostResponseDto;
import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import com.example.blog.vo.Comment;
import com.example.blog.vo.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class APIController {
    private PostService postService;
    private CommentService commentService;
    public APIController(PostService postService, CommentService commentService){
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/api/post")
    public List<PostResponseDto> getPostList(@RequestParam Integer page){
        List<Post> postList = postService.getPostsByPage(page);
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for(Post post: postList){
            postResponseDtoList.add(new PostResponseDto(post));
        }
        return postResponseDtoList;
    }

    @PostMapping("/post")
    public String createPost(@RequestBody PostRequestDto postRequestDto) {
        Post post = postRequestDto.getPost();
        postService.savePost(post);
        return "success";
    }

    @PutMapping("/post")
    public String updatePost(@RequestBody PostRequestDto postRequestDto) {
        Post post = postRequestDto.getPost();
        postService.updatePost(post);
        return "success";
    }

    @DeleteMapping("/post")
    public String deletePost(@RequestParam Integer idx){
        postService.deletePost(idx);
        return "success";
    }

    @GetMapping("/api/comment")
    public List<CommentResponseDto> getCommentList(@RequestParam Integer page, @RequestParam Integer post_idx){
        List<Comment> commentList = commentService.getCommentsByPage(post_idx,page);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for(Comment comment: commentList){
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }
        return commentResponseDtoList;
    }

    @PostMapping("/comment")
    public String createComment(@RequestBody CommentRequestDto commentRequestDto) {
        Comment comment = commentRequestDto.getComment();
        commentService.saveComment(comment);
        return "success";
    }

    @PutMapping("/comment")
    public String updateComment(@RequestBody CommentRequestDto commentRequestDto) {
        Comment comment = commentRequestDto.getComment();
        commentService.updateComment(comment);
        return "success";
    }

    @DeleteMapping("/comment")
    public String deleteComment(@RequestParam Integer idx){
        commentService.deleteComment(idx);
        return "success";
    }
}