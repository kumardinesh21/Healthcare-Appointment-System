package com.Medical_Record._Service.services;

import com.Medical_Record._Service.model.MedicalRecords;
import com.Medical_Record._Service.repo.MedicalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MedicalServices {
    @Autowired
    private MedicalRepo repo;

    public MedicalRecords create(Long patient_id, Long doctor_id, String record_type, MultipartFile file) throws IOException {
        MedicalRecords records = new MedicalRecords();
        records.setDoctorId(doctor_id);
        records.setPatientId(patient_id);
        records.setRecord_type(record_type);
        records.setImage(file.getBytes());
        return repo.save(records);
    }
    public MedicalRecords getByPatient(Long patientId){
        return repo.findByPatientId(patientId);
    }
}

