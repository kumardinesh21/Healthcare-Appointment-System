package com.Medical_Record._Service.conroller;

import com.Medical_Record._Service.model.MedicalRecords;
import com.Medical_Record._Service.services.MedicalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/records")
public class MedicalController {
    @Autowired
    private MedicalServices medicalServices;

    @PostMapping
    public ResponseEntity<?> create(@RequestParam("patient_id") Long patientId,
                                    @RequestParam("doctor_id") Long doctorId,
                                    @RequestParam("record_type") String record_type,
                                    @RequestParam("image") MultipartFile file) {
        try {
            MedicalRecords records = medicalServices.create(patientId, doctorId, record_type, file);
            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping("/pat/{id}")
    public ResponseEntity<?> getByPatient(@PathVariable long id) {
        MedicalRecords byPatient = medicalServices.getByPatient(id);
        return byPatient != null ? new ResponseEntity<>(byPatient, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
