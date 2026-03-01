package com.devtiro.blog.services;

import com.devtiro.blog.domain.dto.AuthResponse;
import com.devtiro.blog.domain.dto.RegisterRequestDto;
import com.devtiro.blog.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    UserDetails authenticate(String email, String password);
    String generateToken(UserDetails userDetails);
    UserDetails validateToken(String token);
    User register (RegisterRequestDto registerRequestDto);
    String generateToken(User newUser);



}
