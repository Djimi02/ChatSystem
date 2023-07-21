package com.example.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.socialmedia.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    
}
