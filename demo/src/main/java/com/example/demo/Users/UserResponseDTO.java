package com.example.demo.Users;

import lombok.Getter;

@Getter 
public class UserResponseDTO {
    private Long id;
    private String login;
    private UserRole role;


    public UserResponseDTO(Long id, String login, UserRole role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }


 
}
