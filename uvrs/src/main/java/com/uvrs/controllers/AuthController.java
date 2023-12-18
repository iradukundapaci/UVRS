package com.uvrs.controllers;

import com.uvrs.models.User;
import com.uvrs.services.AuthenticationService;
import com.uvrs.services.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

  private final AuthenticationService service;

  @Autowired
  private UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<AuthenticationResponse> signUp(
    @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(
    @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @GetMapping("/all")
  public ResponseEntity<?> getAllUsers() {
    try {
      List<User> allUsers = userService.getAllUsers();
      return new ResponseEntity<>(allUsers, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("No content found", HttpStatus.NO_CONTENT);
    }
  }
}
