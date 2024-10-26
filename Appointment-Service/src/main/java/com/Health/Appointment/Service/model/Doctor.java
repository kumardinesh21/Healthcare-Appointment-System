package com.Health.Appointment.Service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    private Long id;
    private String specialization;
    private String availability;
    @OneToOne
    @JsonIgnore
    private User user;

}
