package com.example.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialmedia.model.Chat;
import com.example.socialmedia.model.Message;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.ChatRepository;
import com.example.socialmedia.repository.MessageRepository;
import com.example.socialmedia.repository.UserRepository;

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

    public void addUser(Long chatID, Long userID) {
        Chat chat = chatRepository.findById(chatID).get();

        User user = userRepository.findById(userID).get();

        if (chat == null) {
            throw new IllegalArgumentException("Chat with id=" + chatID + " does not exists.");
        } else if (user == null) {
            throw new IllegalArgumentException("User with id=" + userID + " does not exists.");
        }

        chat.addUser(user);
        chatRepository.save(chat);

        user.addChat(chat);
        userRepository.save(user);
    }

    public void addMessage(Long chatID, Long userID, Message message) {
        Chat chat = chatRepository.findById(chatID).get();

        User user = userRepository.findById(userID).get();

        if (chat == null) {
            throw new IllegalArgumentException("Chat with id=" + chatID + " does not exists.");
        } else if (user == null) {
            throw new IllegalArgumentException("User with id=" + userID + " does not exists.");
        }

        message.setCreator(user);
        message.setChat(chat);
        messageRepository.save(message);

        // chat.addMessage(message);
        // chatRepository.save(chat);

    }

}
