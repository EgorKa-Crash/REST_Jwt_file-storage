package com.example.demoSpringSecurity.dto;

import com.example.demoSpringSecurity.entities.User;
import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String login;
    private String password;
    private String nickName;
    private String email;

    public static User dtoToEntity(AuthenticationRequestDto requestDto){
        User user = new User();
        user.setLogin(requestDto.getLogin());
        user.setPassword(requestDto.getPassword());
        user.setNickName(requestDto.getNickName());
        user.setEmail(requestDto.getEmail());
        return user;
    }
}
