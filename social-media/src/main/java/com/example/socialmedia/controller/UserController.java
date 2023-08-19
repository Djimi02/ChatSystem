package com.example.socialmedia.controller;

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
import com.example.socialmedia.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.retrieveAllUsers();
    }

    @GetMapping("/profile")
    public String getAythenticatedUser() {
        return userService.retrieveAuthenticatedUserInfo();
    }

    @GetMapping("profile/chats")
    public List<Chat> userChats() {
        return userService.retrieveAuthenticatedUser().getChats();
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