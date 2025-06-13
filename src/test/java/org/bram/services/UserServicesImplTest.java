package org.bram.services;

import org.bram.data.repositories.UserRepository;
import org.bram.dtos.requests.UserLoginRequest;
import org.bram.dtos.requests.UserRegisterRequest;
import org.bram.dtos.response.UserLoginResponse;
import org.bram.dtos.response.UserRegisterResponse;
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
    private UserRegisterRequest userRegisterRequest;
    private UserRegisterResponse userRegisterResponse;
    private UserLoginRequest userLoginRequest;
    private UserLoginResponse userLoginResponse;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRegisterRequest = new UserRegisterRequest();
        userRegisterResponse = new UserRegisterResponse();
        userLoginRequest = new UserLoginRequest();
        userLoginResponse = new UserLoginResponse();
    }

    @Test
    public void registerNewUser__registerTest() {
        registerUser();
        assertNotNull(userRegisterResponse.getId());
        assertEquals("Registered successfully", userRegisterResponse.getMessage());
        assertEquals(1, userRepository.count());
    }

    @Test
    public void userCanLogin__loginTest() {
        registerUser();
        userLoginRequest.setUsername("Bram");
        userLoginRequest.setPassword("password");
        userLoginResponse = userServices.login(userLoginRequest);
        assertEquals("Login successfully", userLoginResponse.getMessage());
    }

    private void registerUser() {
        userRegisterRequest.setEmail("bram@fake.com");
        userRegisterRequest.setUsername("Bram");
        userRegisterRequest.setPassword("password");
        userRegisterResponse = userServices.register(userRegisterRequest);
    }


}