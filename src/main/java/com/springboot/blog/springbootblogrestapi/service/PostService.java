package com.springboot.blog.springbootblogrestapi.service;

import java.util.List;

import com.springboot.blog.springbootblogrestapi.payload.PostDto;
import com.springboot.blog.springbootblogrestapi.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto PostDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);


    void deletePostById(long id);

    List<PostDto> getPostsByCategory(Long categoryId);
}
