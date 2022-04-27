package com.example.demoSpringSecurity.rest;

import com.example.demoSpringSecurity.dao.UserDAO;
import com.example.demoSpringSecurity.entities.User;
import com.example.demoSpringSecurity.security.jwt.JwtTokenProvider;
import com.example.demoSpringSecurity.service.impl.UserService;
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

    private final UserService userService;
    private final int TEST_USER = 1;
    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

//    @GetMapping("/greeting")
//    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "greeting";
//    }

//    @PostMapping(value = "/user")
//    public ResponseEntity<?> create(@RequestBody User user) {
//        try {
//            userService.create(user);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

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
        return new ResponseEntity<>(userService.getAllSearchResults(searchBar), HttpStatus.OK);
    }


//    public ResponseEntity<User> getAll(@RequestHeader("Authorization") String token) {
//        String stillToken = jwtTokenProvider.resolveToken(token);
//
//        User user = UserDAO.findByLogin(jwtTokenProvider.getUsername(stillToken));



    @GetMapping(value = "/subscribers")
    public ResponseEntity<List<User>> returnSubscribers(@RequestHeader("Authorization") String token) {
            String stillToken = jwtTokenProvider.resolveToken(token);
            User user = UserDAO.findByLogin(jwtTokenProvider.getUsername(stillToken));
        return new ResponseEntity<>(userService.getSubscribers(user.getUserId()), HttpStatus.OK);
    }

    @GetMapping(value = "/subscriptions")
    public ResponseEntity<List<User>> returnSubscriptions() {
        return new ResponseEntity<>(userService.getSubscriptions(TEST_USER), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<User>> read(@PathVariable("id") long id) {
        final List<User> client = new ArrayList<>();
        client.add(userService.findById(id));
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