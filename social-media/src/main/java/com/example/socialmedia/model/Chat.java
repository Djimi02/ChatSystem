package com.example.socialmedia.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Chat {

    @Id
    @SequenceGenerator(name = "chatSeqGen", allocationSize = 1)
    @GeneratedValue(generator = "chatSeqGen")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "chat", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Message> messages = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "chats", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    public Chat(String name) {
        this.name = name;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void removeMessage(Message message) {
        for (Message message1 : messages) {
            if (message1.getId() == message.getId()) {
                this.messages.remove(message1);
                break;
            }
        }
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        for (User user1 : this.users) {
            if (user1.getId() == user.getId()) {
                this.users.remove(user1);
                break;
            }
        }
    }

    public boolean doesUserExist(Long userID) {
        for (User user : this.users) {
            if (user.getId() == userID) {
                return true;
            }
        }

        return false;
    }

}
