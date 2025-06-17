package org.bram.services;

import org.bram.data.repositories.UserRepository;
import org.bram.dtos.requests.RegisterUserRequest;
import org.bram.dtos.response.RegisterUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServicesImplTest {

    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepository userRepository;
    private RegisterUserRequest registerRequest;
    private RegisterUserResponse registerResponse;


    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        registerRequest = new RegisterUserRequest();
        registerResponse = new RegisterUserResponse();
    }

    @Test
    public void registerUserTest() {


    }

    private void registerUser() {

    }


}