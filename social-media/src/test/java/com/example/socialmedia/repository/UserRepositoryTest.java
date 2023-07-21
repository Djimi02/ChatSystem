package com.example.socialmedia.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.socialmedia.model.User;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void addUser() {
        User user = new User("Gogo", "gogo0@gmail.com");

        repository.save(user);
    }

    @Test
    public void retrieveAllUsers() {
        List<User> users = repository.findAll();

        System.out.println("USERS = " + users);
    }

}
