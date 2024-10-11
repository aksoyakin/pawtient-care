package com.aksoyakin.pawtientcarebe.service.user.impl;

import com.aksoyakin.pawtientcarebe.dto.UserDto;
import com.aksoyakin.pawtientcarebe.dto.converter.EntityConverter;
import com.aksoyakin.pawtientcarebe.dto.request.RegistrationRequest;
import com.aksoyakin.pawtientcarebe.dto.request.UserUpdateRequest;
import com.aksoyakin.pawtientcarebe.exception.ResourceNotFoundException;
import com.aksoyakin.pawtientcarebe.factory.UserFactory;
import com.aksoyakin.pawtientcarebe.model.User;
import com.aksoyakin.pawtientcarebe.repository.UserRepository;
import com.aksoyakin.pawtientcarebe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserFactory userFactory;
    private final UserRepository userRepository;
    private final EntityConverter<User, UserDto> entityConverter;

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

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    @Override
    public void delete(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new ResourceNotFoundException("User not found!");
                });
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(user -> entityConverter.mapEntityToDto(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
