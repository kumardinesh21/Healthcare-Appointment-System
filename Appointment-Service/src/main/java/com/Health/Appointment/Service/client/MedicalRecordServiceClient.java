package com.Health.Appointment.Service.client;

import com.Health.Appointment.Service.model.MedicalRecords;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MEDICAL-RECORD")
public interface MedicalRecordServiceClient {
 @GetMapping("/records/pat/{id}")
 MedicalRecords getByPatient(@PathVariable("id") Long id);
}
