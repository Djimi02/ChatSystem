package com.example.socialmedia.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.socialmedia.model.Chat;
import com.example.socialmedia.model.Message;

@SpringBootTest
public class MessageRepositoryTest {

    @Autowired
    private MessageRepository repository;

    @Test
    public void saveMessage() {
        Chat chat = new Chat("Chat Name");

        Message message = new Message("This is second message!", chat);

        repository.save(message);
    }

    @Test
    public void retrieveAllMessages() {
        List<Message> messages = repository.findAll();

        System.out.println("MESSAGES = " + messages);
    }

}
