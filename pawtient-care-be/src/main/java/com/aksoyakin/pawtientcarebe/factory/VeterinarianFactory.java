package com.aksoyakin.pawtientcarebe.factory;

import com.aksoyakin.pawtientcarebe.dto.request.RegistrationRequest;
import com.aksoyakin.pawtientcarebe.model.User;
import com.aksoyakin.pawtientcarebe.model.Veterinarian;
import com.aksoyakin.pawtientcarebe.repository.VeterinarianRepository;
import com.aksoyakin.pawtientcarebe.service.user.UserAttributeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeterinarianFactory {

    private final VeterinarianRepository veterinarianRepository;
    private final UserAttributeMapper userAttributeMapper;

    public User createVeterinarian(RegistrationRequest registrationRequest) {
        Veterinarian veterinarian = new Veterinarian();
        userAttributeMapper.setCommonAttributes(registrationRequest, veterinarian);
        veterinarian.setSpecialization(registrationRequest.getSpecialization());
        return veterinarianRepository.save(veterinarian);
    }
}
