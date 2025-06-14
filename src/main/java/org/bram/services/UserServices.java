package org.bram.services;

import org.bram.data.models.User;
import org.bram.dtos.requests.ChangePasswordRequest;
import org.bram.dtos.requests.UserLoginRequest;
import org.bram.dtos.requests.UserRegisterRequest;
import org.bram.dtos.response.ChangePasswordResponse;
import org.bram.dtos.response.UserLoginResponse;
import org.bram.dtos.response.UserRegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {

    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);

    UserLoginResponse login(UserLoginRequest loginRequest);

   // ChangePasswordResponse changePassword(User userId, ChangePasswordRequest changePasswordRequest);
}
