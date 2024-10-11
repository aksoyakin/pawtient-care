package com.aksoyakin.pawtientcarebe.service.user;

import com.aksoyakin.pawtientcarebe.dto.request.RegistrationRequest;
import com.aksoyakin.pawtientcarebe.dto.request.UserUpdateRequest;
import com.aksoyakin.pawtientcarebe.model.User;

public interface UserService {

    User register(RegistrationRequest registrationRequest);


    User update(Long userId, UserUpdateRequest userUpdateRequest);
}
