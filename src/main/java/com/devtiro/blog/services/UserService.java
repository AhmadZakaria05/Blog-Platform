package com.devtiro.blog.services;

import com.devtiro.blog.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {
    User getUserById(UUID id);
}
