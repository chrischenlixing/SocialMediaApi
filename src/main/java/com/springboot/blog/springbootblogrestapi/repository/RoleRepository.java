package com.springboot.blog.springbootblogrestapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.springbootblogrestapi.entity.Role;

@Repository
public interface RoleRepository  extends JpaRepository<Role,Long>{

    Optional<Role> findByName(String name);
    
}
