package com.UserService.UserService.service;

import com.UserService.UserService.model.Doctor;
import com.UserService.UserService.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DoctorService {
    @Autowired
    private DoctorRepo repo;
    public Doctor create(Doctor doctor) {
        return repo.save(doctor);
    }

    public List<Doctor> getAll() {
        return repo.findAll();
    }

    public Optional<Doctor> getById(Long id) {
        return repo.findById(id);
    }

    public void delById(Long id) {

        repo.deleteById(id);
    }

}
