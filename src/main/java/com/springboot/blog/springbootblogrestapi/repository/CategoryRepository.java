package com.springboot.blog.springbootblogrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.springbootblogrestapi.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
    

}
