package com.example.simpledataapplication.controller.transferObjects;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String username;
    private String email;

    private String password;

}
