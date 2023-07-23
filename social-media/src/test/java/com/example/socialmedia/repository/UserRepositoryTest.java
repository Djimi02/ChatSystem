package com.example.socialmedia.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.socialmedia.model.Chat;
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
    public void addUserWithChat() {
        User user = new User("Gogo", "gogo0@gmail.com");

        Chat chat = new Chat("Chat name");
        chat.addUser(user);

        user.addChat(chat);

        repository.save(user);
    }

    @Test
    public void retrieveAllUsers() {
        List<User> users = repository.findAll();

        for (User user : users) {
            System.out.println("User name = " + user.getName());
            System.out.println("User is in chats = " + user.getChats().size());
            System.out.println("Chat name = " + user.getChats().get(0).getName());
            System.out.println("Chat members = " + user.getChats().get(0).getUsers().size());
        }

    }

}
