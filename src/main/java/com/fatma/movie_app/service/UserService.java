package com.fatma.movie_app.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public boolean login(String userName, String rawPassword);
}
