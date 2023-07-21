package com.example.socialmedia.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString(exclude = "chat")
public class Message {
    
    @Id
    @SequenceGenerator(name = "messageSeqGen", allocationSize = 1)
    @GeneratedValue(generator = "messageSeqGen")
    private Long id;
    
    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id", referencedColumnName = "id", nullable = false)
    private Chat chat;
    
    public Message(String text, Chat chat) {
        this.text = text;
        this.chat = chat;
    }



}
