package com.Health.Appointment.Service.repo;

import com.Health.Appointment.Service.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
    Appointment findByPatientId(Long patientId);

    Appointment findByDoctorId(Long doctorId);
}
