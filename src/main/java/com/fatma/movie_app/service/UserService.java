package com.fatma.movie_app.service;

import com.fatma.movie_app.exception.RecordNotCorrectException;
import com.fatma.movie_app.model.entity.User;
import com.fatma.movie_app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean login(String userName, String rawPassword) {
        User user = userRepo.findByUserName(userName);
        if (user != null) {
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
