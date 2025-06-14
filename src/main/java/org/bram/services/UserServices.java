package org.bram.services;

import org.bram.dtos.requests.UserLoginRequest;
import org.bram.dtos.requests.UserRegisterRequest;
import org.bram.dtos.response.UserLoginResponse;
import org.bram.dtos.response.UserRegisterResponse;

public interface UserServices {

    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);

    UserLoginResponse login(UserLoginRequest loginRequest);
}
