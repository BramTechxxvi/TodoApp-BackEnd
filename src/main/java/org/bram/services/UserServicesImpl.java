package org.bram.services;

import org.bram.data.models.User;
import org.bram.data.repositories.UserRepository;
import org.bram.dtos.requests.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.*;
import org.springframework.stereotype.Service;


import static org.bram.utils.Mapper.*;
import static org.bram.utils.PasswordUtil.*;


@Service
public class UserServicesImpl implements UserServices {

    private UserRepository userRepository;

    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        verifyNewEmail(request.getEmail());
        User user = map(request);

        User savedUser = userRepository.save(user);
        return map(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new InvalidCredentialsException("Invalid credentials"));

        boolean passwordMatches = verifyPassword(loginRequest.getPassword(), user.getPassword());
        if (!passwordMatches) throw new InvalidCredentialsException("Invalid credentials");

        LoginResponse loginResponse = new LoginResponse();
        String fullName = user.getFirstName() + " " + user.getLastName();
        loginResponse.setMessage("Welcome back " + fullName);

        return loginResponse;

    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        return null;
    }

    private void verifyNewEmail(String email) {
        if (userRepository.existsByEmail(email)) throw new DetailsAlreadyInUseException("Email already exists");
    }

}
