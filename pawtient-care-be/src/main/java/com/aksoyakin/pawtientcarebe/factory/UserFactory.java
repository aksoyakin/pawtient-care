package com.aksoyakin.pawtientcarebe.factory;

import com.aksoyakin.pawtientcarebe.dto.request.RegistrationRequest;
import com.aksoyakin.pawtientcarebe.model.User;

public interface UserFactory {

    User createUser(RegistrationRequest registrationRequest);
}
