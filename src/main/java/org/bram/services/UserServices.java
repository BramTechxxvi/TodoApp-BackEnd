package org.bram.services;

import org.bram.dtos.requests.*;
import org.bram.dtos.response.*;

public interface UserServices {

    RegisterUserResponse registerUser(RegisterUserRequest request);

    LoginResponse login(LoginRequest loginRequest);
    ChangePasswordResponse changePassword(ChangePasswordRequest request);
    ChangeEmailResponse changeEmail(ChangeEmailRequest request);
    UserLogoutResponse logout(UserLogoutRequest request);
}
