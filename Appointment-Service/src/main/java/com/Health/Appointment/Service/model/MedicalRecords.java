package com.Health.Appointment.Service.model;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecords {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String record_type;
    @Lob
    private byte[] image;
}
