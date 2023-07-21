package com.example.socialmedia.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.socialmedia.model.Chat;
import com.example.socialmedia.model.Message;
import com.example.socialmedia.model.User;

@SpringBootTest
public class ChatRepositoryTest {

    @Autowired
    private ChatRepository repository;

    @Test
    public void saveChat() {
        Chat chat = new Chat("name1");

        User user = new User("Masha", "masha@gmail.com");

        chat.addMessage(new Message("Message", chat, user));

        repository.save(chat);
    }

    @Test
    public void retrieveAllChats() {
        List<Chat> chats = repository.findAll();

        System.out.println("CHATS = " + chats);
    }

}
