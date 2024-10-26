package com.Health.Appointment.Service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Doctor doctor;
}
