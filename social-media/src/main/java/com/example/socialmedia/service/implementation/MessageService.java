package com.example.socialmedia.service.implementation;

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

    public Message retrieveMessage(Long messageID) {
        Optional<Message> messageOpt = messageRepository.findById(messageID);
        if (messageOpt.isEmpty()) {
            throw new IllegalArgumentException("Message with id=" + messageID + " does not exists.");
        }
        return messageOpt.get();
    }

    @Transactional
    public void removeCreatorReference(Long messageID) {
        Optional<Message> messageOpt = messageRepository.findById(messageID);
        Message message;
        if (messageOpt.isEmpty()) {
            throw new IllegalArgumentException("Message with id=" + messageID + " does not exists.");
        }
        message = messageOpt.get();

        message.setCreator(null);
    }

}
