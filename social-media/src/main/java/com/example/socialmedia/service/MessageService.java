package com.example.socialmedia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialmedia.model.Message;
import com.example.socialmedia.repository.MessageRepository;

import jakarta.transaction.Transactional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public void removeUserCreatorReference(Long messageID) {
        Optional<Message> messageOpt = messageRepository.findById(messageID);
        Message message;
        if (messageOpt.isEmpty()) {
            throw new IllegalArgumentException("Message with id=" + messageID + " does not exists.");
        }
        message = messageOpt.get();

        message.setCreator(null);
    }
}
