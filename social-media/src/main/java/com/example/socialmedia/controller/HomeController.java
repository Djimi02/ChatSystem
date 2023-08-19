package com.example.socialmedia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "Welcome to the Home Page!";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "My name is Dimitar Manolev and I am 3rd year bachelor Computer Science student at TU/e.";
    }
}
