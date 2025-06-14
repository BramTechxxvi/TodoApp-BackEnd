package org.bram.services;

import org.bram.data.models.User;
import org.bram.data.repositories.TaskRepository;
import org.bram.data.repositories.UserRepository;
import org.bram.dtos.requests.CreateTaskRequest;
import org.bram.dtos.requests.UserLoginRequest;
import org.bram.dtos.requests.UserRegisterRequest;
import org.bram.dtos.response.CreateTaskResponse;
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
    private CreateTaskRequest createTaskRequest;
    private CreateTaskResponse createTaskResponse;
    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRegisterRequest = new UserRegisterRequest();
        userRegisterResponse = new UserRegisterResponse();
        userLoginRequest = new UserLoginRequest();
        userLoginResponse = new UserLoginResponse();
        createTaskRequest = new CreateTaskRequest();
        createTaskResponse = new CreateTaskResponse();
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

    @Test
    public void userCanCreateTask__createTaskTest() {
        userCanLogin__loginTest();
        User userId = userRepository.findById(userLoginResponse.getId()).get();
        createTaskRequest.setTitle("Pickup");
        createTaskRequest.setDescription("I have to pick up a package at Eric's shop on saturday morning");
        createTaskRequest.setDueDate("on the third of may 2025");
        userServices.createTask(userId, createTaskRequest);
        //assertNotNull(createTaskResponse.get());
        assertEquals("Created task successfully", createTaskResponse.getMessage());
        assertEquals(1, taskRepository.count());
        assertEquals(1, userRepository.count());
    }

    private void registerUser() {
        userRegisterRequest.setEmail("bram@fake.com");
        userRegisterRequest.setUsername("Bram");
        userRegisterRequest.setPassword("password");
        userRegisterResponse = userServices.register(userRegisterRequest);
    }


}