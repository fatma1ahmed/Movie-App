package com.fatma.movie_app.service.impl;

import com.fatma.movie_app.model.entity.User;
import com.fatma.movie_app.repository.UserRepo;
import com.fatma.movie_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
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
