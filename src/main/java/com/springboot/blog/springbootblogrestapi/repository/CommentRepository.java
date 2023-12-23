package com.springboot.blog.springbootblogrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.springbootblogrestapi.entity.Comment;
import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByPostId(long postId);
}
