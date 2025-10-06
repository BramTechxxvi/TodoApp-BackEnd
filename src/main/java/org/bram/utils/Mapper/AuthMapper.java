package org.bram.utils.Mapper;

import org.bram.data.models.User;
import org.bram.dtos.requests.RegisterUserRequest;
import org.bram.dtos.response.RegisterUserResponse;

import static org.bram.utils.PasswordUtil.hashPassword;

public class AuthMapper {

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
        String fullName = user.getFirstName() + " " + user.getLastName();
        return new RegisterUserResponse(user.getId(), fullName, "Registration Successful", true);
    }
}
