package org.bram.services;

import org.bram.data.repositories.UserRepository;
import org.bram.dtos.requests.ChangePasswordRequest;
import org.bram.dtos.requests.LoginRequest;
import org.bram.dtos.requests.RegisterUserRequest;
import org.bram.dtos.response.ChangePasswordResponse;
import org.bram.dtos.response.LoginResponse;
import org.bram.dtos.response.RegisterUserResponse;
import org.bram.exceptions.DetailsAlreadyInUseException;
import org.bram.exceptions.InvalidCredentialsException;
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
    private LoginRequest loginRequest;
    private LoginResponse loginResponse;
    private ChangePasswordRequest changePasswordRequest;
    private ChangePasswordResponse changePasswordResponse;


    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        registerRequest = new RegisterUserRequest();
        registerResponse = new RegisterUserResponse();
        loginRequest = new LoginRequest();
        loginResponse = new LoginResponse();
        changePasswordRequest = new ChangePasswordRequest();
        changePasswordResponse = new ChangePasswordResponse();
    }

    @Test
    public void registerUserTest() {
        registerUser();
        assertNotNull(registerResponse.getUserId());
        assertEquals("Registration Successful", registerResponse.getMessage());
        assertEquals(1, userRepository.count());
    }

    @Test
    public void registerNewUserWithExistingEmail__throwsExceptionTest() {
        registerUser();
        RegisterUserRequest newRequest = new RegisterUserRequest();
        newRequest.setFirstName("Adedeji");
        newRequest.setLastName("Ibrahim");
        newRequest.setEmail("grace@ayoola.com");
        newRequest.setPassword("password");
        Exception error = assertThrows(DetailsAlreadyInUseException.class, ()-> userServices.registerUser(newRequest));
        assertEquals("Email already exists", error.getMessage());
    }

    @Test
    public void loginAUser__loginTest() {
        registerUser();
        loginRequest.setEmail("grace@ayoola.com");
        loginRequest.setPassword("123456");
        loginResponse = userServices.login(loginRequest);
        assertEquals("Welcome back Grace Ayoola", loginResponse.getMessage());
    }

    @Test
    public void loginAUserWithInvalidEmail__throwsExceptionTest() {
        registerUser();
        loginRequest.setEmail("grace@ayola.com");
        loginRequest.setPassword("123456");
        Exception error = assertThrows(InvalidCredentialsException.class, ()-> userServices.login(loginRequest));
        assertEquals("Invalid credentials", error.getMessage());
    }

    @Test
    public void loginAUserWithInvalidPassword__throwsExceptionTest() {
        registerUser();
        loginRequest.setEmail("grace@ayoola.com");
        loginRequest.setPassword("12345");
        Exception error = assertThrows(InvalidCredentialsException.class, ()-> userServices.login(loginRequest));
        assertEquals("Invalid credentials", error.getMessage());
    }

    @Test
    public void userCanChangePassword__changePasswordTest() {
        loginAUser__loginTest();
        changePasswordRequest.setOldPassword("123456");
        changePasswordRequest.setNewPassword("password");
        changePasswordResponse = userServices.changePassword(changePasswordRequest);
        assertEquals("Success", changePasswordResponse.getMessage());
    }

    @

    private void registerUser() {
        registerRequest.setFirstName("Grace");
        registerRequest.setLastName("Ayoola");
        registerRequest.setEmail("grace@ayoola.com");
        registerRequest.setPassword("123456");
        registerResponse = userServices.registerUser(registerRequest);
    }


}