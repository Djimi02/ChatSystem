package com.example.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialmedia.model.Chat;
import com.example.socialmedia.service.ChatService;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/save")
    public String saveChat(@RequestBody Chat chat) {
        chatService.saveChat(chat);

        return "New chat is saved.";
    }

    @DeleteMapping("/delete")
    public String deleteChat(@RequestParam(name = "id") Long chatID) {
        chatService.deleteChat(chatID);

        return "Chat with id=" + chatID + " is deleted";
    }

    @PutMapping("/update")
    public String updateChatName(@RequestParam(name = "id") Long chatID, @RequestParam(name = "name") String newName) {
        chatService.updateChatName(chatID, newName);

        return "The name of chat with id=" + chatID + " is updated.";
    }

    @PostMapping("/join")
    public String addUserToChat(@RequestParam(name = "chatid") Long chatID, @RequestParam(name = "userid") Long userID) {
        chatService.addUserToChat(chatID, userID);

        return "User with id=" + userID + " is added to chat with id=" + chatID + ".";
    }

    @PostMapping("/leave")
    public String removeUserFromChat(@RequestParam(name = "chatid") Long chatID, @RequestParam(name = "userid") Long userID) {
        chatService.removeUserChatRelation(chatID, userID);

        return "User with id=" + userID + " is removed from chat with id=" + chatID + ".";
    }
}
