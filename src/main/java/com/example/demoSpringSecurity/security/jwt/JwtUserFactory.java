package com.example.demoSpringSecurity.security.jwt;

import com.example.demoSpringSecurity.model.Status;
import com.example.demoSpringSecurity.model.User;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getPassword(),
                user.getEmail(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated()
        );
    }
}
