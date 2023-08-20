package com.example.socialmedia.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialmedia.model.Chat;
import com.example.socialmedia.model.User;
import com.example.socialmedia.service.ChatService;
import com.example.socialmedia.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private ChatService chatService;

    public UserController(UserService userService, ChatService chatService) {
        this.userService = userService;
        this.chatService = chatService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.retrieveAllUsers();
    }

    @GetMapping("/profile")
    public String getAythenticatedUser() {
        return userService.retrieveAuthenticatedUserInfo();
    }

    @GetMapping("profile/chats")
    public List<Chat> userChats(@RequestParam(name = "id", required = false) Long chatID) {
        if (chatID == null) {
            return userService.retrieveAuthenticatedUser().getChats();
        }

        Chat chat = chatService.retrieveChatById(chatID);

        if (!chat.doesUserExist(userService.retrieveAuthenticatedUser().getId())) {
            throw new IllegalArgumentException("You are not part of the chat with id= " + chatID + ".");
        }

        return Arrays.asList(chat);
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody User user) {

        userService.saveUser(user);

        return "New user is added.";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam(name = "id") Long userID) {
        userService.deleteUser(userID);

        return "User with id=" + userID + " is deleted.";
    }

    @PutMapping("/update")
    public String updateUser(@RequestParam(name = "id") Long userID, @RequestBody User newUser) {
        userService.updateUser(userID, newUser);

        return "User with id=" + userID + " is updated."; 
    }
    
}