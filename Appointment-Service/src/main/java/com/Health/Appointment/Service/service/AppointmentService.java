package com.Health.Appointment.Service.service;



import com.Health.Appointment.Service.client.MedicalRecordServiceClient;
import com.Health.Appointment.Service.client.UserServiceClient;
import com.Health.Appointment.Service.model.Appointment;
import com.Health.Appointment.Service.model.MedicalRecords;
import com.Health.Appointment.Service.model.User;
import com.Health.Appointment.Service.repo.AppointmentRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepo repo;
    @Autowired
    private UserServiceClient client;
    @Autowired
    private MedicalRecordServiceClient  recordServiceClient;

    public Appointment create(Appointment appointment){
        appointment.setDate(new Date());
        return repo.save(appointment);
    }
    public Optional<Appointment> getById(Long id){
        return repo.findById(id);
    }

    public User findByPatientId(Long patientId, String jwtToken) {
        Appointment byPatientId = repo.findByPatientId(patientId);
        if (byPatientId != null) {
            System.out.println("JWT Token being passed: " + jwtToken);  // Log the token
            return client.getById(byPatientId.getPatientId(),jwtToken);
        }
        return null;
    }



    public User findByDoctorId(Long doctor_id, String jwtToken) {
        Appointment byDoctorId = repo.findByDoctorId(doctor_id);
        if (byDoctorId!=null) {
            System.out.println("JWT Token being passed: " + jwtToken);  // Log the token
            return client.getById(byDoctorId.getDoctorId(), jwtToken);
        }
        return null;
    }


    public MedicalRecords findRecords(Long patientId) {
        Appointment byPatientId = repo.findByPatientId(patientId);
        if (byPatientId != null) {
            return  recordServiceClient.getByPatient(patientId);
        }
        return null;
    }
}
