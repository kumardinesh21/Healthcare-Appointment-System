package com.UserService.UserService.service;

import com.UserService.UserService.model.User;
import com.UserService.UserService.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepo repo;

    // Constructor-based injection (recommended for immutability)
    public MyUserDetailService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byName = repo.findByName(username);  // Adjust if this returns Optional<User>

        if (byName != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(byName.getName())
                    .password(byName.getPassword())
                    .roles(byName.getRole())  // Ensure this returns a role string like "ADMIN" or "USER"
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}
