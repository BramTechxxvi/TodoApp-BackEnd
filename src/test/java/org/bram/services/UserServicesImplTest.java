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
    private UserRepository userRepository;
    @Autowired
    private UserServices userServices;
    private RegisterUserRequest registerUserRequest;
    private RegisterUserResponse registerUserResponse;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        registerUserRequest = new RegisterUserRequest();
        registerUserResponse = new RegisterUserResponse();
    }

    @Test
    public void registerNewUser__registerTest() {
        registerUser();
        assertNotNull(registerUserResponse.getId());
        assertEquals(1, userRepository.count());
    }

    @Test
    public void registerNewUser__registerUserTest() {}

    private void registerUser() {
        registerUserRequest.setUsername("username");
        registerUserRequest.setPassword("password");
        registerUserResponse = userServices.register(registerUserRequest);
    }


}