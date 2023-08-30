package com.example.socialmedia.service;

import com.example.socialmedia.model.UserDetailsImpl;

public interface JwtService {
    
    public String extractUserName(String token);

    public String generateToken(UserDetailsImpl userDetails);

    public boolean isTokenValid(String token, UserDetailsImpl userDetails);

}