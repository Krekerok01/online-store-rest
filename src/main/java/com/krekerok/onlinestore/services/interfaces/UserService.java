package com.krekerok.onlinestore.services.interfaces;


import com.krekerok.onlinestore.pojo.LoginRequest;
import com.krekerok.onlinestore.pojo.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> authUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignupRequest signupRequest);
}
