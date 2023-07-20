package com.example.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.socialmedia.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
