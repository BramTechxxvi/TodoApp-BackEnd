package org.bram.services;

import org.bram.data.models.User;
import org.bram.data.repositories.TaskRepository;
import org.bram.data.repositories.UserRepository;
import org.bram.dtos.requests.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.*;
import org.springframework.stereotype.Service;


import java.util.Optional;

import static org.bram.utils.Mapper.*;
import static org.bram.utils.PasswordUtil.*;


@Service
public class UserServicesImpl implements UserServices {

    private UserRepository userRepository;

    public UserServicesImpl(UserRepository userRepository, TaskRepository taskRepository) {
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

        user.setLoggedIn(true);
        userRepository.save(user);
        LoginResponse loginResponse = new LoginResponse();
        String fullName = user.getFirstName() + " " + user.getLastName();
        loginResponse.setUserId(user.getId());
        loginResponse.setMessage("Welcome back " + fullName);

        return loginResponse;
    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        boolean isSamePassword = request.getNewPassword().equals(request.getOldPassword());
        if (isSamePassword) throw new SamePasswordException("New password cannot be the same as the old password");

        boolean isCorrect = verifyPassword(request.getOldPassword(), user.getPassword());
        if (!isCorrect) throw new IncorrectOldPasswordException("Old password not correct");

        user.setPassword(hashPassword(request.getNewPassword()));
        userRepository.save(user);
        ChangePasswordResponse response = new ChangePasswordResponse();
        response.setMessage("Password changed successfully");

        return response;
    }

    @Override
    public ChangeEmailResponse changeEmail(ChangeEmailRequest request) {
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (optionalUser.isEmpty()) throw new UserNotFoundException("User not found");

        User user = optionalUser.get();
        boolean isSameEmail = request.getNewEmail().equals(request.getOldEmail());
        if (isSameEmail) throw new SameEmailException("New email cannot be same as old email");

        boolean isOldEmail = request.getOldEmail().equals(user.getEmail());
        if(!isOldEmail) throw new IncorrectOldEmailException("Old email not correct");

        user.setEmail(request.getNewEmail());
        userRepository.save(user);

        ChangeEmailResponse response = new ChangeEmailResponse();
        response.setMessage("Email changed successfully");
        return response;
    }

    @Override
    public UserLogoutResponse logout(UserLogoutRequest request) {
        return null;
    }

    private void verifyNewEmail(String email) {
        if (userRepository.existsByEmail(email)) throw new DetailsAlreadyInUseException("Email already exists");
    }


}
