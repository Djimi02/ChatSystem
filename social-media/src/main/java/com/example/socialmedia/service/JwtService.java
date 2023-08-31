package com.example.socialmedia.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.socialmedia.model.UserDetailsImpl;

public interface JwtService {
    
    public String extractUserName(String token);

    public String generateToken(UserDetailsImpl userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);

}