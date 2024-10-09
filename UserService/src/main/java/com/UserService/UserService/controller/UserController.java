package com.UserService.UserService.controller;

import com.UserService.UserService.model.User;
import com.UserService.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        if (user != null ) {
            User user1 = service.create(user);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<User> all = service.getAll();
        return new ResponseEntity<>(all, HttpStatus.FOUND);

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<User> byId = service.getById(id);
        return byId.isPresent() ?
                new ResponseEntity<>(byId, HttpStatus.FOUND) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delById(@PathVariable Long id) {
        Optional<User> byId = service.getById(id);
        if (byId.isPresent()) {
            service.delById(id);
            return new ResponseEntity<>(byId, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User user) {
        Optional<User> byId = service.getById(id);
        if (byId.isPresent()) {
            User user1 = byId.get();
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setUsername(user.getUsername());
            service.create(user1);
            return new ResponseEntity<>(byId,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
