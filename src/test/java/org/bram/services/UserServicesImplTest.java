package org.bram.services;

import org.bram.data.repositories.UserRepository;
import org.bram.dtos.requests.ChangePasswordRequest;
import org.bram.dtos.requests.UserLoginRequest;
import org.bram.dtos.requests.UserRegisterRequest;
import org.bram.dtos.response.ChangePasswordResponse;
import org.bram.dtos.response.UserLoginResponse;
import org.bram.dtos.response.UserRegisterResponse;
import org.bram.exceptions.DetailsAlreadyInUseException;
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
    private ChangePasswordRequest changePasswordRequest;
    private ChangePasswordResponse changePasswordResponse;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRegisterRequest = new UserRegisterRequest();
        userRegisterResponse = new UserRegisterResponse();
        userLoginRequest = new UserLoginRequest();
        userLoginResponse = new UserLoginResponse();
        changePasswordRequest = new ChangePasswordRequest();
        changePasswordResponse = new ChangePasswordResponse();
    }

    @Test
    public void registerNewUser__registerTest() {
        registerUser();
        assertNotNull(userRegisterResponse.getId());
        assertEquals("Registered successfully", userRegisterResponse.getMessage());
        assertEquals(1, userRepository.count());
    }

    @Test
    public void registerNewUserWithRegisteredEmail__throwsException() {
        registerUser();
        userRegisterRequest.setEmail("bram@fake.com");
        userRegisterRequest.setUsername("Ola");
        userRegisterRequest.setPassword("password");
        Exception error = assertThrows(DetailsAlreadyInUseException.class, ()-> userServices.register(userRegisterRequest));
        assertEquals("Email already exists", error.getMessage());
    }

    @Test
    public void registerNewUserWithRegisteredUsername__throwsException() {
        registerUser();
        userRegisterRequest.setEmail("ola@fake.com");
        userRegisterRequest.setUsername("Bram");
        userRegisterRequest.setPassword("password");
        Exception error = assertThrows(DetailsAlreadyInUseException.class, ()-> userServices.register(userRegisterRequest));
        assertEquals("User already exists", error.getMessage());
    }

    @Test
    public void userCanLogin__loginTest() {
        registerUser();
        userLoginRequest.setUsername("Bram");
        userLoginRequest.setPassword("password");
        userLoginResponse = userServices.login(userLoginRequest);
        assertEquals("Login successfully", userLoginResponse.getMessage());
    }

    @Test
    public void userCanChangePassword__changePasswordTest() {
        userCanLogin__loginTest();
        changePasswordRequest.setOldPassword("password");
        changePasswordRequest.setNewPassword("newPassword");
        changePasswordResponse = userServices.changePassword(changePasswordRequest);
        assertEquals("Password changed successfully", changePasswordResponse.getMessage());
    }

    private void registerUser() {
        userRegisterRequest.setEmail("bram@fake.com");
        userRegisterRequest.setUsername("Bram");
        userRegisterRequest.setPassword("password");
        userRegisterResponse = userServices.register(userRegisterRequest);
    }


}