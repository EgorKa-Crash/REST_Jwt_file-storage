package com.example.demoSpringSecurity.dto;

import com.example.demoSpringSecurity.entities.User;
import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;

    public static User dtoToEntity(AuthenticationRequestDto requestDto){
        User user = new User();
        user.setLogin(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());
        return user;
    }
}
