package org.bram.services;

import org.bram.data.repositories.UserRepository;
import org.bram.dtos.requests.RegisterUserRequest;
import org.bram.dtos.response.RegisterUserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    private UserRepository userRepository;

    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        return null;
    }
}
