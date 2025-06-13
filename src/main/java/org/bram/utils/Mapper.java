package org.bram.utils;

import org.bram.data.models.User;
import org.bram.dtos.requests.UserRegisterRequest;
import org.bram.dtos.response.UserRegisterResponse;

public class Mapper {

    public static User map(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setEmail(userRegisterRequest.getEmail());
        user.setUsername(userRegisterRequest.getUsername());
        user.setPassword(userRegisterRequest.getPassword());

        return user;
    }

    public static UserRegisterResponse map(User user) {
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setId(user.getId());
        userRegisterResponse.setUsername(user.getUsername());
        userRegisterResponse.setMessage("Registered successfully");

        return userRegisterResponse;
    }
}
