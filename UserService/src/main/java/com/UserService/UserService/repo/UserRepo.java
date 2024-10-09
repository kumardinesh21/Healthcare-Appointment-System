package com.UserService.UserService.repo;

import com.UserService.UserService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo  extends JpaRepository<User,Long> {
}
