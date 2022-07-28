package com.example.demoSpringSecurity.service.impl;

import com.example.demoSpringSecurity.entities.User;

import java.util.List;

public interface IUserService {

      User register(User user);

      List<User> getAll();

      User findByLogin(String login);

      User findByLoginSecure(String login, String password);

      User findById(Long id);

      void delete(Long id);

//      /**
//       * Возвращает список всех имеющихся клиентов
//       * @return список клиентов
//       */
//      List<Userr> readAll();
//
//      /**
//       * Возвращает клиента по его ID
//       * @param id - ID клиента
//       * @return - объект клиента с заданным ID
//       */
//      Userr read(int id);


      void update(User user);

      List<User> getSubscribers(long id);
      List<User> getSubscriptions(long id);
      List<User> getAllSearchResults(String substring);

      List<User> readAll();
}
