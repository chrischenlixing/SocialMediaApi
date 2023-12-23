package com.springboot.blog.springbootblogrestapi.payload;

import lombok.Data;

/**
 * CommentDto
 */

 @Data
public class CommentDto {

    private long id;
    private String name;
    private String email;
    private String body;
}