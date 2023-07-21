package com.example.socialmedia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Message {
    
    @Id
    @SequenceGenerator(name = "messageSeqGen", allocationSize = 1)
    @GeneratedValue(generator = "messageSeqGen")
    private Long id;
    private String text;
    
    public Message(String text) {
        this.text = text;
    }

}
