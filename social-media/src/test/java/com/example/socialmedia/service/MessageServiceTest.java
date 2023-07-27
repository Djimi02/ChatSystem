package com.example.socialmedia.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.socialmedia.model.Message;

@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageService service;

    @Test
    public void removeCreatorReferenceTest() {
        Long messageID = 1l;

        service.removeCreatorReference(messageID);
    }

    @Test
    public void retrieveMessageTest() {
        Long messageID = 1l;

        Message message = service.retrieveMessage(messageID);

        System.out.println("MESSAGE = " + message);
    }
}
