package com.aksoyakin.pawtientcarebe.service.user;

import com.aksoyakin.pawtientcarebe.dto.request.RegistrationRequest;
import com.aksoyakin.pawtientcarebe.factory.UserFactory;
import com.aksoyakin.pawtientcarebe.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserFactory userFactory;

    public User add(RegistrationRequest registrationRequest) {
        return userFactory.createUser(registrationRequest);
    }
}
