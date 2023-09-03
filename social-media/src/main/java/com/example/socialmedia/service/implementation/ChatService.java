package com.example.socialmedia.service.implementation;

import java.util.ArrayList;
import java.util.List;
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
    
    private ChatRepository chatRepository;
    private UserRepository userRepository;
    private MessageRepository messageRepository;
    private UserService userService;

    public ChatService(ChatRepository chatRepository, UserRepository userRepository, MessageRepository messageRepository, UserService userService) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    @Autowired
    @Lazy
    private ChatService chatService;

    public Chat retrieveChatById(Long chatID) {
        Optional<Chat> chatOPT = chatRepository.findById(chatID);
        if (chatOPT.isEmpty()) {
            throw new IllegalArgumentException("Chat with id= " + chatID + " does not exist!");
        }

        return chatOPT.get();
    }

    public Long saveChat(Chat chat) {
        if (chat.getName() == null) {
            throw new IllegalArgumentException("Chat name should be provided.");
        } else if (chat.getName().isEmpty()) {
            throw new IllegalArgumentException("Chat name should be provided.");
        }

        return this.chatRepository.save(chat).getId();
    }

    /**
     * Removes all relations between users that are in the chat and the chat, and 
     * deletes all messages associated with this chat. Finally, deletes the chat
     * itself.
     * @param chatID - the id of the chat to be deleted
     * @throws IllegalArgumentException when no chat exists with the provided id
     */
    public void deleteChat(Long chatID) {
        Optional<Chat> chatOpt = chatRepository.findById(chatID);
        Chat chat;
        if (chatOpt.isPresent()) {
            chat = chatOpt.get();
        } else {
            throw new IllegalArgumentException("Chat with id=" + chatID + " does not exists.");
        }

        if (!chat.doesUserExist(userService.retrieveAuthenticatedUser().getId())) {
            throw new IllegalArgumentException("You are not part of the chat with id= " + chatID + ".");
        }

        List<User> users = new ArrayList<>(chat.getUsers());
        for (User user : users) {
            chatService.removeUserChatRelation(chatID, user.getId());
        }
        chatRepository.delete(chat);
    }

    public void updateChatName(Long chatID, String chatName) {
        Optional<Chat> chatOpt = chatRepository.findById(chatID);
        Chat chat;
        if (chatOpt.isPresent()) {
            chat = chatOpt.get();
        } else {
            throw new IllegalArgumentException("Chat with id=" + chatID + " does not exists.");
        }

        if (!chat.doesUserExist(userService.retrieveAuthenticatedUser().getId())) {
            throw new IllegalArgumentException("You are not part of the chat with id= " + chatID + ".");
        }

        chat.setName(chatName);
        chatRepository.save(chat);
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

        if (chat.doesUserExist(userID)) {
            throw new IllegalArgumentException("User with id=" + userID + " is already part of the chat with id=" + chatID + ".");
        }

        chat.addUser(user);
        user.addChat(chat);
    }

    public void addUserToChat(Long chatID) {
        chatService.addUserToChat(chatID, userService.retrieveAuthenticatedUser().getId());
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

        if (!chat.doesUserExist(userID)) {
            throw new IllegalArgumentException("User with id=" + userID + " is not part of the chat with id=" + chatID + ".");
        }

        chat.removeUser(user);
        user.removeChat(chat);
    }

    public void removeUserChatRelation(Long chatID) {
        chatService.removeUserChatRelation(chatID, userService.retrieveAuthenticatedUser().getId());
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

    public void addMessageToChat(Long chatID, Message message) {
        chatService.addMessageToChat(chatID, userService.retrieveAuthenticatedUser().getId(), message);
    }

    public void deleteMessage(Long messageID) {
        if (messageRepository.findById(messageID).isEmpty()) {
            throw new IllegalArgumentException("Message with id=" + messageID + " does not exists.");
        }

        messageRepository.deleteById(messageID);
    }

}