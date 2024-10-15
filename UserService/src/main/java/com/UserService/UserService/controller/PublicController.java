package com.UserService.UserService.controller;

import com.UserService.UserService.model.Doctor;
import com.UserService.UserService.model.User;
import com.UserService.UserService.service.MyUserDetailService;
import com.UserService.UserService.service.UserService;
import com.UserService.UserService.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private JwtUtils utils;

    @PostMapping("/reg")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user != null ) {
            if ("DOCTOR".equals(user.getRole())) {
                Doctor doctor = new Doctor();
                doctor.setSpecialization(user.getDoctor().getSpecialization());
                doctor.setAvailability(user.getDoctor().getAvailability());
                doctor.setUser(user);
                user.setDoctor(doctor);
            }else {
                user.setDoctor(null);
            }
            User user1 = service.create(user);
            return new ResponseEntity<>(user1, HttpStatus.CREATED); // Use CREATED status for successful registration
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
            UserDetails userDetails = myUserDetailService.loadUserByUsername(user.getName());

            // Generate JWT token
            String jwt = utils.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Login error for user {}: {}", user.getName(), e.getMessage()); // Log meaningful message
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED); // User-friendly message
        }
    }
}
