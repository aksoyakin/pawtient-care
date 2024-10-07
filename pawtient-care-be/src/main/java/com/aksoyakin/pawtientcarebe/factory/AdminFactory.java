package com.aksoyakin.pawtientcarebe.factory;

import com.aksoyakin.pawtientcarebe.dto.request.RegistrationRequest;
import com.aksoyakin.pawtientcarebe.model.Admin;
import com.aksoyakin.pawtientcarebe.model.User;
import com.aksoyakin.pawtientcarebe.repository.AdminRepository;
import com.aksoyakin.pawtientcarebe.service.user.UserAttributeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminFactory {

    private final AdminRepository adminRepository;
    private final UserAttributeMapper userAttributeMapper;

    public User createAdmin(RegistrationRequest registrationRequest) {
        Admin admin = new Admin();
        userAttributeMapper.setCommonAttributes(registrationRequest, admin);
        return adminRepository.save(admin);
    }
}
