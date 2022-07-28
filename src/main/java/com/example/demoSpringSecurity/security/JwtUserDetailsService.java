package com.example.demoSpringSecurity.security;

import com.example.demoSpringSecurity.dao.UserDAO;
import com.example.demoSpringSecurity.entities.User;
import com.example.demoSpringSecurity.security.jwt.JwtUser;
import com.example.demoSpringSecurity.security.jwt.JwtUserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = UserDAO.findByLogin(login);
        if (user == null)
            throw new UsernameNotFoundException("User with username: " + login + " not found");
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", login);
        return jwtUser;
    }
}
