package com.aksoyakin.pawtientcarebe.factory;

import com.aksoyakin.pawtientcarebe.dto.request.RegistrationRequest;
import com.aksoyakin.pawtientcarebe.model.Patient;
import com.aksoyakin.pawtientcarebe.model.User;
import com.aksoyakin.pawtientcarebe.repository.PatientRepository;
import com.aksoyakin.pawtientcarebe.service.user.UserAttributeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientFactory {

    private final PatientRepository patientRepository;
    private final UserAttributeMapper userAttributeMapper;

    public User createPatient(RegistrationRequest registrationRequest) {
        Patient patient = new Patient();
        userAttributeMapper.setCommonAttributes(registrationRequest, patient);
        return patientRepository.save(patient);
    }
}
