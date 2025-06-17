package org.bram.services;

import org.bram.dtos.requests.LoginRequest;
import org.bram.dtos.requests.RegisterUserRequest;
import org.bram.dtos.response.LoginResponse;
import org.bram.dtos.response.RegisterUserResponse;

public interface UserServices {

    RegisterUserResponse registerUser(RegisterUserRequest request);
    LoginResponse login(LoginRequest loginRequest);
}
