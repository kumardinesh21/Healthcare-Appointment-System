package com.Medical_Record._Service.repo;

import com.Medical_Record._Service.model.MedicalRecords;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRepo extends JpaRepository<MedicalRecords, Long> {
    MedicalRecords findByPatientId(Long patientId);
}
