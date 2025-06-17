package org.bram.services;

import org.bram.data.repositories.UserRepository;
import org.bram.dtos.requests.RegisterUserRequest;
import org.bram.dtos.response.RegisterUserResponse;
import org.bram.exceptions.DetailsAlreadyInUseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.bram.utils.PasswordUtil.*;
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
        newRequest.setPassword(hashPassword("password"));
        Exception error = assertThrows(DetailsAlreadyInUseException.class, ()-> userServices.registerUser(newRequest));
        assertEquals("Email already exists", error.getMessage());
    }

    @Test
    public void loginAUser__loginTest() {
        registerUser();
    }

    private void registerUser() {
        registerRequest.setFirstName("Grace");
        registerRequest.setLastName("Ayoola");
        registerRequest.setEmail("grace@ayoola.com");
        registerRequest.setPassword(hashPassword("123456"));
        registerResponse = userServices.registerUser(registerRequest);
    }


}