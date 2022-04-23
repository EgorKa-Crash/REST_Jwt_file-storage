package com.example.demoSpringSecurity.security.jwt;

import com.example.demoSpringSecurity.entities.Status;
import com.example.demoSpringSecurity.entities.User;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user){
        return new JwtUser(
                user.getUserId(),
                user.getLogin(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated()
        );
    }
}
