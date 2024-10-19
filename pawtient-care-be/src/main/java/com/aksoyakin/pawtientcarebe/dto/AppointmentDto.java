package com.aksoyakin.pawtientcarebe.dto;

import com.aksoyakin.pawtientcarebe.model.enums.AppointmentStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class AppointmentDto {

    private Long id;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private String reason;

    private AppointmentStatus status;

    private String appointmentNo;

    private UserDto patient;

    private UserDto veterinarian;

    private List<PetDto> pets;
}
