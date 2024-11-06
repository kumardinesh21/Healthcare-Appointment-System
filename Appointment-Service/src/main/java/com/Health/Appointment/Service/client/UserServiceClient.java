package com.Health.Appointment.Service.client;

import com.Health.Appointment.Service.model.MedicalRecords;
import com.Health.Appointment.Service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "USERSERVICE")
public interface UserServiceClient {
 @GetMapping("/user/id/{id}")
 User getById(@PathVariable("id") Long id, @RequestHeader("Authorization") String token);
}

