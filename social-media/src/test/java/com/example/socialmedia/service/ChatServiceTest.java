package com.example.socialmedia.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.socialmedia.model.Chat;
import com.example.socialmedia.model.Message;

@SpringBootTest
public class ChatServiceTest {

    @Autowired
    private ChatService service;

    public void saveChatTest() {
        Chat chat = new Chat("Chat Group 1");

        service.saveChat(chat);
    }

    @Test
    public void addUserTest() {
        Long chatID = 1l;
        Long userID = 1l;

        service.addUser(chatID, userID);
    }

    @Test
    public void addMessageTest() {
        Long chatID = 1l;
        Long userID = 1l;
        Message message = new Message("Message 2 in chat 1.");

        service.addMessage(chatID, userID, message);
    }

    @Test
    public void retrieve() {
    }

}
