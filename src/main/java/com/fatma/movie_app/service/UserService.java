package com.fatma.movie_app.service;

import com.fatma.movie_app.model.dto.LoginResponse;
import com.fatma.movie_app.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public LoginResponse login(String userName, String rawPassword);
}
