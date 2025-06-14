package org.bram.services;

import org.bram.data.models.User;
import org.bram.data.repositories.UserRepository;
import org.bram.dtos.requests.CreateTaskRequest;
import org.bram.dtos.requests.UserLoginRequest;
import org.bram.dtos.requests.UserRegisterRequest;
import org.bram.dtos.response.CreateTaskResponse;
import org.bram.dtos.response.UserLoginResponse;
import org.bram.dtos.response.UserRegisterResponse;
import org.bram.exceptions.DetailsAlreadyInUseException;
import org.bram.exceptions.InvalidCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.bram.utils.Mapper.map;

@Service
public class UserServicesImpl implements UserServices {

    private UserRepository userRepository;

    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        verifyNewEmail(userRegisterRequest.getEmail());
        verifyNewUsername(userRegisterRequest.getUsername());

        User user = map(userRegisterRequest);
        User savedUser = userRepository.save(user);

        return map(savedUser);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());
        if (optionalUser.isEmpty()) throw new InvalidCredentialsException("Invalid credentials");

        User user = optionalUser.get();
        if (!user.getPassword().equals(loginRequest.getPassword())) throw new InvalidCredentialsException("Invalid credentials");

        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setUsername(user.getUsername());
        userLoginResponse.setId(user.getId());
        userLoginResponse.setMessage("Login successfully");
        return userLoginResponse;
    }

    private void verifyNewEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) throw new DetailsAlreadyInUseException("Email already exists");
    }
    private void verifyNewUsername(String username) {
        if (userRepository.findByUsername(username).isPresent()) throw new DetailsAlreadyInUseException("User already exists");
    }


}
