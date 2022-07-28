package com.example.demoSpringSecurity.rest;

import com.example.demoSpringSecurity.dao.SubscriptionsDAO;
import com.example.demoSpringSecurity.dao.UserDAO;
import com.example.demoSpringSecurity.dto.SubscriptionsIds;
import com.example.demoSpringSecurity.entities.User;
import com.example.demoSpringSecurity.security.jwt.JwtTokenProvider;
import com.example.demoSpringSecurity.service.impl.ISubscriptionsService;
import com.example.demoSpringSecurity.service.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;
    private final ISubscriptionsService subscriptionsService;
    private final int TEST_USER = 1;
    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public UserController(IUserService userService, ISubscriptionsService subscriptionsService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.subscriptionsService = subscriptionsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @GetMapping(value = "")
    public ResponseEntity<List<User>> read() {
        final List<User> users = userService.readAll();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/searchBar", params = {"searchBar"},
            method = RequestMethod.GET)
    public ResponseEntity<List<User>> read1(@RequestParam("searchBar") String searchBar) {
        if (searchBar.equals("all")) searchBar = "";
        return new ResponseEntity<>(userService.getAllSearchResults(searchBar), HttpStatus.OK);
    }

    @GetMapping(value = "/subscribers")
    public ResponseEntity<List<User>> returnSubscribers(@RequestHeader("Authorization") String token) {
        String stillToken = jwtTokenProvider.resolveToken(token);
        User user = UserDAO.findByLogin(jwtTokenProvider.getUsername(stillToken));
        System.out.println("запрос подписчиков");
        return new ResponseEntity<>(userService.getSubscribers(user.getUserId()), HttpStatus.OK);
    }

    @GetMapping(value = "/subscriptions")
    public ResponseEntity<List<User>> returnSubscriptions(@RequestHeader("Authorization") String token) {
        String stillToken = jwtTokenProvider.resolveToken(token);
        User user = UserDAO.findByLogin(jwtTokenProvider.getUsername(stillToken));
        System.out.println("запрос подписок");
        return new ResponseEntity<>(userService.getSubscriptions(user.getUserId()), HttpStatus.OK);
    }

    @PostMapping(value = "/isSubscriber")
    public ResponseEntity<Boolean> read1(@RequestBody SubscriptionsIds subscriptionsIds) {
        Boolean b = subscriptionsService.isContainSubscription(subscriptionsIds);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @PostMapping(value = "/subscribe")
    public ResponseEntity<Boolean> read(@RequestBody SubscriptionsIds subscriptionsIds) {
        Boolean b = subscriptionsService.isContainSubscription(subscriptionsIds);
        if (b) {
            System.out.println("Отписаться");
            SubscriptionsDAO.remove(subscriptionsIds);
        } else {
            System.out.println("Подписаться");
            SubscriptionsDAO.add(subscriptionsIds);
        }
        b = !b;
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<User>> read(@PathVariable("id") long id) {
        final List<User> client = new ArrayList<>();
        client.add(userService.findById(id));
        System.out.println("получение пользователя по id");
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody User client) {
        userService.update(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}