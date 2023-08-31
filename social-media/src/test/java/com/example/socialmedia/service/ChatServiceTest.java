package com.example.socialmedia.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.socialmedia.model.Chat;
import com.example.socialmedia.model.Message;
import com.example.socialmedia.service.implementation.ChatService;

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
    public void addUserToChatTest() {
        // Long chatID = 1l;
        // Long userID = 2l;

        service.addUserToChat(1l, 3l);
        // service.addUserToChat(1l, 2l);
        // service.addUserToChat(2l, 1l);
        // service.addUserToChat(2l, 2l);
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
    public void addMessageToChatTest() {
        Long chatID = 1l;
        Long userID = 3l;
        Message message = new Message("Message 4 in chat 1.");

        service.addMessageToChat(chatID, userID, message);
        // service.addMessageToChat(1l, 2l, new Message("Masha in chat 1 message"));
        // service.addMessageToChat(1l, 3l, new Message("Gogo in chat 1 message"));
        // service.addMessageToChat(2l, 2l, new Message("Masha in chat 2 message"));
        // service.addMessageToChat(2l, 3l, new Message("Gogo in chat 2 message"));
        // service.addMessageToChat(3l, 3l, new Message("Masha in chat 3 message"));
    }

    @Test
    public void addMessageToChatWithWrongUserTest() {
        Long chatID = 3l;
        Long userID = 1l;
        Message message = new Message("Message 2 in chat 1.");

        try {
            service.addMessageToChat(chatID, userID, message);

            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void retrieve() {
    }

    @Test
    public void removeUserFromChatTest() {
        Long userID = 1l;
        Long chatID = 2l;

        service.removeUserChatRelation(chatID, userID);
    }

    @Test
    public void deleteChatTest() {
        Long chatID = 1l;

        service.deleteChat(chatID);
    }

    @Test
    public void deleteMessageTest() {
        Long messageID = 3l;

        service.deleteMessage(messageID);
    }

    @Test
    public void updateChatNameTest() {
        Long chatID = 4l;
        String newChatName = "New Chat Name";

        service.updateChatName(chatID, newChatName);
    }

}