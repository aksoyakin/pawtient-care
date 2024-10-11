package com.aksoyakin.pawtientcarebe.service.user.impl;

import com.aksoyakin.pawtientcarebe.dto.request.RegistrationRequest;
import com.aksoyakin.pawtientcarebe.dto.request.UserUpdateRequest;
import com.aksoyakin.pawtientcarebe.exception.ResourceNotFoundException;
import com.aksoyakin.pawtientcarebe.factory.UserFactory;
import com.aksoyakin.pawtientcarebe.model.User;
import com.aksoyakin.pawtientcarebe.repository.UserRepository;
import com.aksoyakin.pawtientcarebe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserFactory userFactory;
    private final UserRepository userRepository;

    @Override
    public User register(RegistrationRequest registrationRequest) {
        return userFactory.createUser(registrationRequest);
    }

    @Override
    public User update(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = findById(userId);
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setGender(userUpdateRequest.getGender());
        user.setPhoneNumber(userUpdateRequest.getPhoneNumber());
        user.setSpecialization(userUpdateRequest.getSpecialization());
        return userRepository.save(user);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }
}
