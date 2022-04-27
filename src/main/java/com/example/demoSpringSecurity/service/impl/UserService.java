package com.example.demoSpringSecurity.service.impl;

import com.example.demoSpringSecurity.entities.User;

import java.util.List;

public interface UserService {

      User register(User user);

      List<User> getAll();

      User findByLogin(String login);

      User findByLoginSecure(String login, String password);

      User findById(Long id);

      void delete(Long id);
}
