package com.uvrs.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.uvrs.models.User;
import com.uvrs.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User signUp(User user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            throw new RuntimeException("Username already in use");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        // Save the user to the database
        return userRepository.save(user);
    }

    public User login(User user){
        Optional<User> optionalUser = userRepository.findByUserNameOrEmail(user.getUserName(), user.getEmail());

        if (optionalUser.isPresent()){
            User usr = optionalUser.get();
            if (Objects.equals(usr.getPassword(), user.getPassword())){
                return usr;
            }
        }
        throw new RuntimeException();
    }
}