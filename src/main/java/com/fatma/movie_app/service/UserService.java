package com.fatma.movie_app.service;

import com.fatma.movie_app.exception.RecordNotCorrectException;
import com.fatma.movie_app.model.entity.User;
import com.fatma.movie_app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User login(String userName, String password){
        return userRepo.findByUserName(userName).orElseThrow(
                ()->new RecordNotCorrectException("userName or password Not correct")
        );
    }
    public  User addUser(User user){
        userRepo.save(user);
        return user;
    }
}
