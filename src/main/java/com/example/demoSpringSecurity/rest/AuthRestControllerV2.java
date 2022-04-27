package com.example.demoSpringSecurity.rest;

import com.example.demoSpringSecurity.dao.UserDAO;
import com.example.demoSpringSecurity.entities.User;
import com.example.demoSpringSecurity.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/token")
public class AuthRestControllerV2 {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthRestControllerV2(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getAll(@RequestHeader("Authorization") String token) {
        String stillToken = jwtTokenProvider.resolveToken(token);

        User user = UserDAO.findByLogin(jwtTokenProvider.getUsername(stillToken));
        return new ResponseEntity<>(user, HttpStatus.OK);

//        return user != null
//                ? new ResponseEntity<>(user, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
