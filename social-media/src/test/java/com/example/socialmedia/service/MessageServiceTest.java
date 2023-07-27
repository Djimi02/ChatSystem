package com.example.socialmedia.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageService service;

    @Test
    public void removeUserCreatorReferenceTest() {
        Long messageID = 1l;

        service.removeUserCreatorReference(messageID);
    }
}
