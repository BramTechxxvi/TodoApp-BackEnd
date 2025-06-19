package org.bram.utils;

import org.bram.data.models.User;
import org.bram.dtos.requests.RegisterUserRequest;
import org.bram.dtos.response.RegisterUserResponse;

import static org.bram.utils.PasswordUtil.hashPassword;

public class Mapper {

    public static User map(RegisterUserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName().trim());
        user.setLastName(request.getLastName().trim());
        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setLoggedIn(false);
        user.setPassword(hashPassword(request.getPassword().trim()));

        return user;
    }

    public static RegisterUserResponse map(User user) {
        RegisterUserResponse response = new RegisterUserResponse();
        response.setUserId(user.getId());
        String fullName = user.getFirstName() + " " + user.getLastName();
        response.setFullName(fullName);
        response.setSuccess(true);
        response.setMessage("Registration Successful");

        return response;
    }
}
