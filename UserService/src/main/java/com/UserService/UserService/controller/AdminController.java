package com.UserService.UserService.controller;
import com.UserService.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.UserService.UserService.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService service;
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<User> all = service.getAll();
        return new ResponseEntity<>(all, HttpStatus.FOUND);

    }
}
