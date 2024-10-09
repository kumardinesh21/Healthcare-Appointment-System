package com.UserService.UserService.repo;

import com.UserService.UserService.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor ,Long> {
}
