package com.uvrs.services;

import com.uvrs.config.Jwtservice;
import com.uvrs.controllers.AuthenticationRequest;
import com.uvrs.controllers.AuthenticationResponse;
import com.uvrs.controllers.RegisterRequest;
import com.uvrs.models.Role;
import com.uvrs.models.User;
import com.uvrs.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final Jwtservice jwtservice;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User
      .builder()
      .firstName(request.getFirstname())
      .lastName(request.getLastname())
      .email(request.getEmail())
      .phone(request.getPhone())
      .userName(request.getUsername())
      .passwrd(passwordEncoder.encode(request.getPassword()))
      .role(Role.USER)
      .build();

    repository.save(user);
    var jwtToken = jwtservice.generateToken(user);

    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword()
      )
    );
    var user = repository.findByEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtservice.generateToken(user);

    return AuthenticationResponse.builder().token(jwtToken).build();
  }
}
