package com.example.socialmedia.service;

import com.example.socialmedia.request.SignInRequest;
import com.example.socialmedia.request.SignUpRequest;
import com.example.socialmedia.response.JwtAuthenticationResponse;

public interface AuthService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
