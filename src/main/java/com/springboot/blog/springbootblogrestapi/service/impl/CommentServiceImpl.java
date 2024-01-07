package com.springboot.blog.springbootblogrestapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.springbootblogrestapi.entity.Comment;
import com.springboot.blog.springbootblogrestapi.entity.Post;
import com.springboot.blog.springbootblogrestapi.exception.BlogApiException;
import com.springboot.blog.springbootblogrestapi.payload.ResourceNotFoundException;
import com.springboot.blog.springbootblogrestapi.payload.CommentDto;
import com.springboot.blog.springbootblogrestapi.repository.CommentRepository;
import com.springboot.blog.springbootblogrestapi.repository.PostRepository;
import com.springboot.blog.springbootblogrestapi.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", postId));
            
        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);
        CommentDto commentResponse = mapToDTO(newComment);
        return commentResponse;

    }

    private CommentDto mapToDTO(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
        // CommentDto commentDto = new CommentDto();
        // commentDto.setBody(comment.getBody());
        // commentDto.setEmail(comment.getEmail());
        // commentDto.setId(comment.getId());
        // commentDto.setName(comment.getName());
        return commentDto;

    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
        // Comment comment= new Comment();
        // comment.setBody(commentDto.getBody());
        // comment.setEmail(commentDto.getEmail());
        // comment.setId(commentDto.getId());
        // comment.setName(commentDto.getName());
        return comment;
    }


    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }


    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
        () -> new ResourceNotFoundException("Post", "id", postId));
            
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
            new ResourceNotFoundException("Comment", "id", commentId));
    

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToDTO(comment);
    }


    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentRequest) {
        Post post = postRepository.findById(postId).orElseThrow(
        () -> new ResourceNotFoundException("Post", "id", postId));
            
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
            new ResourceNotFoundException("Comment", "id", commentId));
    

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDTO(updatedComment);
    }


    @Override
    public void deleteCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
        () -> new ResourceNotFoundException("Post", "id", postId));
            
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
            new ResourceNotFoundException("Comment", "id", commentId));
    

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        commentRepository.delete(comment);
    }
}
