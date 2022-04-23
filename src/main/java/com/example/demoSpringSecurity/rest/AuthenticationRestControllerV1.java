package com.example.demoSpringSecurity.rest;

import com.example.demoSpringSecurity.dto.AuthenticationRequestDto;
import com.example.demoSpringSecurity.model.User;
import com.example.demoSpringSecurity.security.jwt.JwtTokenProvider;
import com.example.demoSpringSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
            String username = requestDto.getUsername();

            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username " + username + " not found");
            }
            String token = jwtTokenProvider.createToken(username);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

    }

    @PostMapping("/register")
    public ResponseEntity registration(@RequestBody AuthenticationRequestDto requestDto) {
        User user = userService.register(AuthenticationRequestDto.dtoToEntity(requestDto));
        userService.register(user);
        Map<Object, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        return ResponseEntity.ok(response);
    }
}
