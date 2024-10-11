package com.aksoyakin.pawtientcarebe.service.user;

import com.aksoyakin.pawtientcarebe.dto.UserDto;
import com.aksoyakin.pawtientcarebe.dto.request.RegistrationRequest;
import com.aksoyakin.pawtientcarebe.dto.request.UserUpdateRequest;
import com.aksoyakin.pawtientcarebe.model.User;

import java.util.List;

public interface UserService {

    User register(RegistrationRequest registrationRequest);

    User update(Long userId, UserUpdateRequest userUpdateRequest);

    User findById(Long userId);

    void delete(Long userId);

    List<UserDto> getAllUsers();
}
