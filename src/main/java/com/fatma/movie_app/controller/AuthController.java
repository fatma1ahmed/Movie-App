package com.fatma.movie_app.controller;

import com.fatma.movie_app.model.dto.LoginRequest;
import com.fatma.movie_app.model.entity.User;
import com.fatma.movie_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest){
        boolean isAuthenticated= userService.login(loginRequest.getUsername(),loginRequest.getPassword());
        if(isAuthenticated){
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
