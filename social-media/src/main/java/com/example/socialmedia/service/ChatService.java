package com.example.socialmedia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialmedia.model.Chat;
import com.example.socialmedia.model.Message;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.ChatRepository;
import com.example.socialmedia.repository.MessageRepository;
import com.example.socialmedia.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ChatService {
    
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    public void saveChat(Chat chat) {
        if (chat.getName() == null || chat.getName().isEmpty()) {
            throw new IllegalArgumentException("Chat name should be provided.");
        }

        this.chatRepository.save(chat);
    }

    @Transactional
    public void addUserToChat(Long chatID, Long userID) {
        Optional<Chat> chatOpt = chatRepository.findById(chatID);
        Chat chat;
        if (chatOpt.isPresent()) {
            chat = chatOpt.get();
        } else {
            throw new IllegalArgumentException("Chat with id=" + chatID + " does not exists.");
        }

        Optional<User> userOpt = userRepository.findById(userID);
        User user;
        if (userOpt.isPresent()) {
            user = userOpt.get();
        } else {
            throw new IllegalArgumentException("User with id=" + userID + " does not exists.");
        }

        chat.addUser(user);
        user.addChat(chat);
    }

    public void addMessageToChat(Long chatID, Long userID, Message message) {
        Optional<Chat> chatOpt = chatRepository.findById(chatID);
        Chat chat;
        if (chatOpt.isPresent()) {
            chat = chatOpt.get();
        } else {
            throw new IllegalArgumentException("Chat with id=" + chatID + " does not exists.");
        }

        Optional<User> userOpt = userRepository.findById(userID);
        User user;
        if (userOpt.isPresent()) {
            user = userOpt.get();
        } else {
            throw new IllegalArgumentException("User with id=" + userID + " does not exists.");
        }

        message.setCreator(user);
        message.setChat(chat);
        messageRepository.save(message);
    }

    @Transactional
    public void removeUserFromChat(Long chatID, Long userID) {
        Optional<Chat> chatOpt = chatRepository.findById(chatID);
        Chat chat;
        if (chatOpt.isPresent()) {
            chat = chatOpt.get();
        } else {
            throw new IllegalArgumentException("Chat with id=" + chatID + " does not exists.");
        }

        Optional<User> userOpt = userRepository.findById(userID);
        User user;
        if (userOpt.isPresent()) {
            user = userOpt.get();
        } else {
            throw new IllegalArgumentException("User with id=" + userID + " does not exists.");
        }

        chat.removeUser(user);
        user.removeChat(chat);
    }

}