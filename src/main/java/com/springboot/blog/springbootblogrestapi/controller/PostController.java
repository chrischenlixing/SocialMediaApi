package com.springboot.blog.springbootblogrestapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.springbootblogrestapi.payload.PostDto;
import com.springboot.blog.springbootblogrestapi.payload.PostResponse;
import com.springboot.blog.springbootblogrestapi.service.PostService;
import com.springboot.blog.springbootblogrestapi.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }


    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
        
    }

    @GetMapping
    public PostResponse getAllPosts(
        @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_DIRECTION, required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable long id){
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("post entity deleted successfully.", HttpStatus.OK);

    }
}
