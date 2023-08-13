package com.example.socialmedia.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.socialmedia.model.Chat;
import com.example.socialmedia.model.Message;
import com.example.socialmedia.model.User;

@SpringBootTest
public class MessageRepositoryTest {

    @Autowired
    private MessageRepository repository;

    @Test
    public void saveMessage() {
        Chat chat = new Chat("Chat Name");

        User user = new User("Gogo", "gogo@gmail.com", "pass", "User");

        Message message = new Message("This is second message!", chat, user);

        repository.save(message);
    }

    @Test
    public void retrieveAllMessages() {
        List<Message> messages = repository.findAll();

        System.out.println("MESSAGES = " + messages);
    }

}
