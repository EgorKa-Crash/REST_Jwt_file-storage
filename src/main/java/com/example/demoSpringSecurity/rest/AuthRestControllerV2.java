package com.example.demoSpringSecurity.rest;

import com.example.demoSpringSecurity.model.User;
import com.example.demoSpringSecurity.security.jwt.JwtTokenProvider;
import com.example.demoSpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/security/")
public class AuthRestControllerV2 {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthRestControllerV2(JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public String getAll(@RequestHeader("Authorization") String token) {
        String stillToken = jwtTokenProvider.resolveToken(token);

        return jwtTokenProvider.getUsername(stillToken);
    }
}
