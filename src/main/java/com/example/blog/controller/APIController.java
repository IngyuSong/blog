package com.example.blog.controller;

import com.example.blog.dto.CommentRequestDto;
import com.example.blog.dto.CommentResponseDto;
import com.example.blog.dto.PostRequestDto;
import com.example.blog.dto.PostResponseDto;
import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import com.example.blog.vo.Comment;
import com.example.blog.vo.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class APIController {
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/post")
    public List<PostResponseDto> getPostList(@RequestParam Integer page) {
        List<Post> postList = postService.getPostByPage(page, 3);
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for (Post post : postList) {
            postResponseDtoList.add(new PostResponseDto(post));
        }
        return postResponseDtoList;
    }

    @PostMapping("/post")
    public String createPost(@RequestBody PostRequestDto postDto) {
        Post post = postDto.getPost();
        postService.savePost(post);

        return "success";
    }

    @PutMapping("/post")
    public String updatePost(@RequestBody PostRequestDto postDto) {
        Post post = postDto.getPost();
        postService.updatePost(post);
        return "success";
    }

    @DeleteMapping("/post")
    public String deletePost(@RequestParam Integer postId) {
        postService.deletePost(postId);
        return "success";
    }

    @GetMapping("/comment")
    public List<CommentResponseDto> getCommentListByPost(@RequestParam Integer postId, @RequestParam Integer page) {
        List<Comment> commentList = commentService.getCommentByPostIdAndPage(postId, page, 3);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }
        return commentResponseDtoList;
    }

    @PostMapping("/comment")
    public String createComment(@RequestBody CommentRequestDto commentDto) {
        Comment comment = commentDto.getComment();
        commentService.saveComment(comment);
        return "Success";
    }

    @PutMapping("/comment")
    public String updateComment(@RequestBody CommentRequestDto commentDto) {
        Comment comment = commentDto.getComment();
        commentService.updateComment(comment);
        return "Success";
    }

    @DeleteMapping("/comment")
    public String deleteComment(@RequestParam Integer commentId) {
        commentService.deleteComment(commentId);
        return "Success";
    }
}