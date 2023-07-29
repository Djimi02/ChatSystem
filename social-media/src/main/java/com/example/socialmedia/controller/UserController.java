package com.example.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialmedia.model.User;
import com.example.socialmedia.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    private List<User> getAllUsers() {
        return userService.retrieveAllUsers();
    }

    @PostMapping("/save")
    private String saveUser(@RequestBody User user) {

        userService.saveUser(user);

        return "New user is added.";
    }

    @DeleteMapping("/delete")
    private String deleteUser(@RequestParam(name = "id") Long userID) {
        userService.deleteUser(userID);

        return "User with id=" + userID + " is deleted.";
    }
    
}
