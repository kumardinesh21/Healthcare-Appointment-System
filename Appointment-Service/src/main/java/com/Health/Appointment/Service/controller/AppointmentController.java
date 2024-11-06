package com.Health.Appointment.Service.controller;

import com.Health.Appointment.Service.model.Appointment;
import com.Health.Appointment.Service.model.MedicalRecords;
import com.Health.Appointment.Service.model.User;
import com.Health.Appointment.Service.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/appoint")
public class AppointmentController {
    @Autowired
    private AppointmentService service;

    @PostMapping
    public ResponseEntity<?> saver(@RequestBody Appointment appointment) {
        if (appointment != null) {
            Appointment appointment1 = service.create(appointment);
            return new ResponseEntity<>(appointment1, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Optional<Appointment> byId = service.getById(id);
        return byId.isPresent() ?
                new ResponseEntity<>(byId, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/pat/{patient_id}")
    public ResponseEntity<?> findByPatientId(@PathVariable Long patient_id, @RequestHeader("Authorization") String jwtToken) {
        User byPatientId = service.findByPatientId(patient_id, jwtToken);
        return byPatientId != null ? new ResponseEntity<>(byPatientId, HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/doc/{doctor_id}")
    public ResponseEntity<?> findByDoctorId(@PathVariable Long doctor_id, @RequestHeader("Authorization") String jwtToken) {
        User byDoctorIdId = service.findByDoctorId(doctor_id, jwtToken);
        return byDoctorIdId != null ?
                new ResponseEntity<>(byDoctorIdId, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/rec/{patient_id}")
    public ResponseEntity<?> findRecords(@PathVariable Long patient_id) {
        MedicalRecords byPatientId = service.findRecords(patient_id);
        return byPatientId != null ? new ResponseEntity<>(byPatientId, HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Appointment appointment) {
        Optional<Appointment> byId = service.getById(id);
        if (byId.isPresent()) {
            Appointment put = byId.get();
            put.setDate(appointment.getDate());
            put.setDoctorId(appointment.getDoctorId());
            put.setStatus(appointment.getStatus());
            put.setPatientId(appointment.getPatientId());
            Appointment appointment1 = service.create(put);
            return new ResponseEntity<>(appointment1, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}