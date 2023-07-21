package com.example.socialmedia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private String name;

    public Chat(String name) {
        this.name = name;
    }

}
