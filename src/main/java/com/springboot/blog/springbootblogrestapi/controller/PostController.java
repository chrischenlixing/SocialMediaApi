package com.springboot.blog.springbootblogrestapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "CRUD REST APIs for Post Resource")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Create Post REST API", description = "Create Post REST API is used to save post into database")

    @ApiResponse(responseCode = "201", description = "Http Status 201 CREATED")

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping 
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }

    @Operation(summary = "Get All Posts REST API", description = "Get All Posts REST API is used to fetch all the posts from the database")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    @GetMapping 
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_DIRECTION, required = false) String sortDir) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);

    }

    @Operation(summary = "Get Post By Id REST API V1", description = "Get Post By Id REST API is used to get single post from the database")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    @GetMapping(value = "{id}")
    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // @Operation(summary = "Get Post By Id REST API V2", description = "Get Post By Id REST API is used to get single post from the database, please put application/vnd.chris.v2+json in your Accept header if you want to use V2")
    // @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    // @GetMapping(value = "{id}",produces = "application/vnd.chris.v2+json")
    // public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable long id) {
    //     PostDto postDto = postService.getPostById(id);
    //     PostDtoV2 postDtoV2 = new PostDtoV2();
    //     postDtoV2.setId(postDto.getId());
    //     postDtoV2.setTitle(postDto.getTitle());
    //     postDtoV2.setDescription(postDto.getDescription());
    //     postDtoV2.setContent(postDto.getContent());
    //     List<String> tags = new ArrayList<>();
    //     tags.add("Java");
    //     tags.add("Spring Boot");
    //     tags.add("AWS");

    //     postDtoV2.setTags(tags);
    //     return ResponseEntity.ok(postDtoV2);
    // }

    @Operation(summary = "update Post REST API", description = "Update Post REST API is used to update a particular post in the database")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable long id) {
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @Operation(summary = "Delete Post REST API", description = "Delete Post REST API is used to delete a particular post from the database")
    @ApiResponse(responseCode = "200", description = "Http Status 200 SUCCESS")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("post entity deleted successfully.", HttpStatus.OK);

    }

    @GetMapping("category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Long categoryId) {
        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }
}
