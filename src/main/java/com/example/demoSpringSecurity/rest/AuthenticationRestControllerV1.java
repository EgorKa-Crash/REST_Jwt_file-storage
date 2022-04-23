package com.example.demoSpringSecurity.rest;

import com.example.demoSpringSecurity.dto.AuthenticationRequestDto;
import com.example.demoSpringSecurity.entities.User;
import com.example.demoSpringSecurity.security.jwt.JwtTokenProvider;
import com.example.demoSpringSecurity.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth/")
public class AuthenticationRestControllerV1 {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestControllerV1(JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String login = requestDto.getUsername();

            User user = userService.findByLogin(login);
            if (user == null) {
                throw new UsernameNotFoundException("User with login " + login + " not found");
            }
            String token = jwtTokenProvider.createToken(login);

            Map<Object, Object> response = new HashMap<>();
            response.put("login", login);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid login or password");
        }

    }

    @PostMapping("/register")
    public ResponseEntity registration(@RequestBody AuthenticationRequestDto requestDto) {
        User user = userService.register(AuthenticationRequestDto.dtoToEntity(requestDto));
        userService.register(user);
        Map<Object, Object> response = new HashMap<>();
        response.put("login", user.getLogin());
        return ResponseEntity.ok(response);
    }
}
