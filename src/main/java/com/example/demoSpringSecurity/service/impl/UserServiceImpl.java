package com.example.demoSpringSecurity.service.impl;

import com.example.demoSpringSecurity.dao.UserDAO;
import com.example.demoSpringSecurity.entities.Status;
import com.example.demoSpringSecurity.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    //private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl() {
        //this.userRepo = userRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);

        UserDAO.insertUser(user);
        log.info("IN register - user: {} successfully registered", user);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> result = UserDAO.getAllOfUsers();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByLogin(String login) {
        User result = UserDAO.findByLogin(login);
        log.info("IN findByUsername - {} found by username: {}", result, login);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = UserDAO.getUser(id);
        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }
        log.info("IN findByUsername - {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(Long id) {
        UserDAO.deleteUser(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }
}
