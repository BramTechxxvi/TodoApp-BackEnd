package org.bram.services;

import org.bram.data.models.User;
import org.bram.data.repositories.UserRepository;
import org.bram.dtos.requests.LoginRequest;
import org.bram.dtos.requests.RegisterUserRequest;
import org.bram.dtos.response.LoginResponse;
import org.bram.dtos.response.RegisterUserResponse;
import org.bram.exceptions.DetailsAlreadyInUseException;
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
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        verifyNewEmail(request.getEmail());
        User user = map(request);

        User savedUser = userRepository.save(user);
        return map(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    private void verifyNewEmail(String email) {
        for(User user : userRepository.findAll()) {
            if (user.getEmail().equals(email)) throw new DetailsAlreadyInUseException("Email already exists");
        }
    }

}
