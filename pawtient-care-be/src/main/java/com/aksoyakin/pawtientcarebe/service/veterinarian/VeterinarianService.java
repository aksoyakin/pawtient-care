package com.aksoyakin.pawtientcarebe.service.veterinarian;

import com.aksoyakin.pawtientcarebe.dto.UserDto;
import com.aksoyakin.pawtientcarebe.model.Veterinarian;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface VeterinarianService {
    List<UserDto> getAllVeterinariansWithDetails();

    List<Veterinarian> getVeterinariansBySpecialization(String specialization);

    List<UserDto> findAvailableVetsForAppointment(String specialization,
                                                  LocalDate date,
                                                  LocalTime time);
}
