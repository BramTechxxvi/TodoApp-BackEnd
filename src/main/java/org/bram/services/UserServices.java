package org.bram.services;

import org.bram.dtos.requests.RegisterUserRequest;
import org.bram.dtos.response.RegisterUserResponse;

public interface UserServices {

    RegisterUserResponse register(RegisterUserRequest registerUserRequest);
}
