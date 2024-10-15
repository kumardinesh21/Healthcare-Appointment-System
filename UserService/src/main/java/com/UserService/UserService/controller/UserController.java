package com.UserService.UserService.controller;

import com.UserService.UserService.model.User;
import com.UserService.UserService.security.MyUserAuthentication;
import com.UserService.UserService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private MyUserAuthentication authentication;




//    @GetMapping("{id}")
//    public ResponseEntity<?> getById(@PathVariable Long id) {
//        Optional<User> byId = service.getById(id);
//        return byId.isPresent() ?
//                new ResponseEntity<>(byId, HttpStatus.FOUND) :
//                new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//
//    }

    @DeleteMapping()
    public ResponseEntity<?> delByName() {
        String name = authentication.getUser();
        User byName = service.findByName(name);
        service.delByName(byName);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody User user) {
        String user1 = authentication.getUser();
        User byName = service.findByName(user1);
        byName.setName(user.getName());
        byName.setRole(user.getRole());
        byName.setPassword(user.getPassword());
        byName.setEmail(user.getEmail());
         service.create(byName);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<?> getByName() {
        String name = authentication.getUser();
        User byName = service.findByName(name);
        return  new ResponseEntity<>(byName, HttpStatus.OK) ;
    }

}
