package com.uvrs.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uvrs.models.User;
import com.uvrs.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User userRequest) {
        try {
            User newUser = userService.signUp(userRequest);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userRequest) {
        try {
            User newUser = userService.login(userRequest);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid username/email or password");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        try{
            List<User> allUsers = userService.getAllUsers();
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No content found", HttpStatus.NO_CONTENT);
        }
    }
    
    
}
