package com.springboot.blog.springbootblogrestapi.service;

import java.util.List;

import com.springboot.blog.springbootblogrestapi.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    
    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updateComment(long postId,long commentId, CommentDto commentRequest);

    void deleteCommentById(Long postId, Long commentId);
} 

