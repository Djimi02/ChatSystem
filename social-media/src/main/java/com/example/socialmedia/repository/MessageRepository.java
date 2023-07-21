package com.example.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.socialmedia.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    
}
