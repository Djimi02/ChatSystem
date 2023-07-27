package com.example.socialmedia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @Autowired
    @Lazy
    private ChatService chatService;

    public void saveChat(Chat chat) {
        if (chat.getName() == null || chat.getName().isEmpty()) {
            throw new IllegalArgumentException("Chat name should be provided.");
        }

        this.chatRepository.save(chat);
    }

    /**
     * Removes all relations between users that are in the chat and the chat and 
     * deletes all messages associated with this chat. Finally, deletes the chat
     * itself.
     * @param chatID - the id of the chat to be deleted
     */
    public void deleteChat(Long chatID) {
        Optional<Chat> chatOpt = chatRepository.findById(chatID);
        Chat chat;
        if (chatOpt.isPresent()) {
            chat = chatOpt.get();
        } else {
            throw new IllegalArgumentException("Chat with id=" + chatID + " does not exists.");
        }

        for (User user : chat.getUsers()) {
            chatService.removeUserChatRelation(chatID, user.getId());
        }
        chatRepository.delete(chat);
    }

    /* ================================== CHAT - USER ============================================ */

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

    @Transactional
    public void removeUserChatRelation(Long chatID, Long userID) {
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

    /* ================================== CHAT - MESSAGE ============================================ */

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

        if (!chat.doesUserExist(userID)) {
            throw new IllegalArgumentException("User with id=" + userID + " is not part of the chat with id= " + chatID);
        }

        message.setCreator(user);
        message.setChat(chat);
        messageRepository.save(message);
    }

    public void deleteMessage(Long messageID) {
        if (messageRepository.findById(messageID).isEmpty()) {
            throw new IllegalArgumentException("Message with id=" + messageID + " does not exists.");
        }

        messageRepository.deleteById(messageID);
    }

}