package com.krekerok.onlinestore.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    private String username;
    private String password;
    private String email;
}
