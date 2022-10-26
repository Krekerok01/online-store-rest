package com.krekerok.onlinestore.services.interfaces;


import com.krekerok.onlinestore.dto.requests.LoginRequest;
import com.krekerok.onlinestore.dto.requests.SignupRequest;
import com.krekerok.onlinestore.entities.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> authUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignupRequest signupRequest);

    User getUserByUsername(String username);
}
