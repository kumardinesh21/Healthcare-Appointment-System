package com.Health.Appointment.Service.client;

import com.Health.Appointment.Service.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "UserService")
public interface Client {
 @GetMapping("/user/id/{id}")
 User getById(@PathVariable Long id, @RequestHeader("Authorization") String token);
}
