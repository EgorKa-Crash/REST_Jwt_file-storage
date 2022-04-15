package com.example.demoSpringSecurity.security;

import com.example.demoSpringSecurity.model.User;
import com.example.demoSpringSecurity.repo.UserRepo;
import com.example.demoSpringSecurity.security.jwt.JwtUser;
import com.example.demoSpringSecurity.security.jwt.JwtUserFactory;
import com.example.demoSpringSecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public JwtUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
