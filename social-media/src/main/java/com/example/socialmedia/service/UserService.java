package com.example.socialmedia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.socialmedia.model.Chat;
import com.example.socialmedia.model.Message;
import com.example.socialmedia.model.User;
import com.example.socialmedia.model.UserDetailsImpl;
import com.example.socialmedia.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User should not be null upon saving.");
        } else if (user.getName() == null) {
            throw new IllegalArgumentException("User should have name upon saving.");
        } else if (user.getName().isBlank()) {
            throw new IllegalArgumentException("User should have name upon saving.");
        } else if (user.getEmail() == null) {
            throw new IllegalArgumentException("User should have email upon saving.");
        } else if (!emailMatches(user.getEmail())) {
            throw new IllegalArgumentException("User should have valid email upon saving.");
        }

        userRepository.save(user);
    }

    public boolean emailMatches(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }

    public void deleteUser(Long userID) {

        Optional<User> userOpt = userRepository.findById(userID);
        User user;
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User with id=" + userID + " does not exist.");
        }
        user = userOpt.get();

        List<Chat> chats = new ArrayList<>(user.getChats());

        for (Chat chat : chats) {
            chatService.removeUserChatRelation(chat.getId(), userID);

            // removes the references of all messages in the chat to this user
            for (Message message : chat.getMessages()) {
                if (message.getCreator() == null) {
                    continue;
                } else if (message.getCreator().getId() == userID) {
                    messageService.removeCreatorReference(message.getId());
                }
            }

        }

        userRepository.deleteById(userID);
    }

    public void updateUser(Long userID, User newUser) {
        Optional<User> userOpt = userRepository.findById(userID);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User with id=" + userID + " does not exist.");
        }
        
        newUser.setId(userID);

        userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User with email=" + email + " does not exist.");
        }

        return new UserDetailsImpl(userOpt.get());
    }

    public String retrieveAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return "No user is authenticated!";
        }

        Optional<User> userOpt = userRepository.findByEmail(authentication.getName());
        if (userOpt.isEmpty()) {
            return "Bruh!";
        }
        User user = userOpt.get();

        String output = "Name: " + user.getName() + "\n" + 
                        "Email: " + user.getEmail() + "\n" + 
                        "Role: " + user.getRole() + "\n" + 
                        "Number of chats: " + user.getChats().size();

        return output;
    }
}