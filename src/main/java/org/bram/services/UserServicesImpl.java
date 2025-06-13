package org.bram.services;

import org.bram.data.models.User;
import org.bram.data.repositories.UserRepository;
import org.bram.dtos.requests.RegisterUserRequest;
import org.bram.dtos.response.RegisterUserResponse;
import org.springframework.stereotype.Service;

import static org.bram.utils.Mapper.map;

@Service
public class UserServicesImpl implements UserServices {

    private UserRepository userRepository;

    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        User user = map(registerUserRequest);
        User savedUser = userRepository.save(user);

        return map(savedUser);
    }
}
