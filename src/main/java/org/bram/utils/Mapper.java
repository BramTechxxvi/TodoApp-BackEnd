package org.bram.utils;

import org.bram.data.models.User;
import org.bram.dtos.requests.RegisterUserRequest;
import org.bram.dtos.response.RegisterUserResponse;

public class Mapper {

    public static User map(RegisterUserRequest  registerUserRequest) {
        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(registerUserRequest.getPassword());

        return user;
    }

    public static RegisterUserResponse map(User user) {
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setId(user.getId());
        registerUserResponse.setUsername(user.getUsername());
        registerUserResponse.setMessage("Registered successfully");

        return registerUserResponse;
    }
}
