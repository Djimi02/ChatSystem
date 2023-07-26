package com.example.socialmedia.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.socialmedia.model.Chat;
import com.example.socialmedia.model.Message;

@SpringBootTest
public class ChatServiceTest {

    @Autowired
    private ChatService service;

    @Test
    public void saveChatTest() {
        Chat chat = new Chat("Chat Group 1");

        service.saveChat(chat);
    }

    @Test
    public void addUserTest() {
        Long chatID = 1l;
        Long userID = 1l;

        service.addUserToChat(chatID, userID);
    }

    @Test
    public void addUserWithWrongChatID() {
        Long chatID = -1l;
        Long userID = 1l;

        try {
            service.addUserToChat(chatID, userID);

            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
        
    }

    @Test
    public void addMessageTest() {
        Long chatID = 1l;
        Long userID = 1l;
        Message message = new Message("Message 2 in chat 1.");

        service.addMessageToChat(chatID, userID, message);
    }

    @Test
    public void retrieve() {
    }

    @Test
    public void removeUserFromChatTest() {
        Long userID = 1l;
        Long chatID = 1l;

        service.removeUserFromChat(chatID, userID);
    }

}