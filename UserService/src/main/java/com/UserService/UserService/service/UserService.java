package com.UserService.UserService.service;

import com.UserService.UserService.model.User;
import com.UserService.UserService.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;
   @Autowired
    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public Optional<User> getById(Long id) {
        return repo.findById(id);
    }

    public void delByName(User user) {
         repo.delete(user);

    }


    public User findByName(String name){
        return  repo.findByName(name);
    }

}
