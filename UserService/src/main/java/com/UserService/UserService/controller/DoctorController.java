package com.UserService.UserService.controller;

import com.UserService.UserService.model.Doctor;
import com.UserService.UserService.model.User;
import com.UserService.UserService.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doc")
public class DoctorController {
    @Autowired
    private DoctorService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Doctor doctor) {
        if (doctor != null) {
            Doctor doctor1 = service.create(doctor);
            return new ResponseEntity<>(doctor1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Doctor> all = service.getAll();
        return new ResponseEntity<>(all, HttpStatus.FOUND);

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Doctor> byId = service.getById(id);
        return byId.isPresent() ?
                new ResponseEntity<>(byId, HttpStatus.FOUND) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delById(@PathVariable Long id) {
        Optional<Doctor> byId = service.getById(id);
        if (byId.isPresent()) {
            service.delById(id);
            return new ResponseEntity<>(byId, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Doctor doctor) {
        Optional<Doctor> byId = service.getById(id);
        if (byId.isPresent()) {
            Doctor doctor1 = byId.get();
            doctor1.setName(doctor.getName());
            doctor1.setAvailability(doctor.getAvailability());
            doctor1.setSpecialization(doctor.getSpecialization());
            service.create(doctor1);
            return new ResponseEntity<>(byId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
