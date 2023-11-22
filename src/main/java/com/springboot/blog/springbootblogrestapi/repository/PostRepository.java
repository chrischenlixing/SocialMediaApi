package com.springboot.blog.springbootblogrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.springbootblogrestapi.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    
    
}
