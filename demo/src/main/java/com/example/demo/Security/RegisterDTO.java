package com.example.demo.Security;

import com.example.demo.Users.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}