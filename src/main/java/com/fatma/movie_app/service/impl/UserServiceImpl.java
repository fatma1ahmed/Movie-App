package com.fatma.movie_app.service.impl;

import com.fatma.movie_app.exception.RecordNotCorrectException;
import com.fatma.movie_app.exception.RecordNotFoundException;
import com.fatma.movie_app.model.dto.LoginResponse;
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

    public LoginResponse login(String userName, String rawPassword) {
        User user=userRepo.findByUserName(userName);
        if (user == null) {
            throw new RecordNotCorrectException("User not found");
        }
        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            return new LoginResponse(user.getId(), user.getUserName(),user.getPassword(), user.getRole());
        } else {
            throw new RecordNotCorrectException("Invalid credentials");
        }
    }

}
