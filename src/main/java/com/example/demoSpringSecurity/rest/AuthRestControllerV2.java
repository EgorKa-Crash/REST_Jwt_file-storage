package com.example.demoSpringSecurity.rest;

import com.example.demoSpringSecurity.dao.UserDAO;
import com.example.demoSpringSecurity.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security/")
public class AuthRestControllerV2 {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthRestControllerV2(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/getAll")
    public String getAll(@RequestHeader("Authorization") String token) {
        String stillToken = jwtTokenProvider.resolveToken(token);

        return UserDAO.findByLogin(jwtTokenProvider.getUsername(stillToken)).toString();
    }
}
