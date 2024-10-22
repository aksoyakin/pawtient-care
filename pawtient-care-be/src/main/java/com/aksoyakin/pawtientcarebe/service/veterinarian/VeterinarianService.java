package com.aksoyakin.pawtientcarebe.service.veterinarian;

import com.aksoyakin.pawtientcarebe.dto.UserDto;

import java.util.List;

public interface VeterinarianService {
    List<UserDto> getAllVeterinariansWithDetails();
}
