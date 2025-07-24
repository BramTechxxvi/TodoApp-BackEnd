package org.bram.services;

import org.bram.data.models.Task;
import org.bram.data.models.TaskStatus;
import org.bram.data.models.User;
import org.bram.data.repositories.TaskRepository;
import org.bram.data.repositories.UserRepository;
import org.bram.dtos.requests.*;
import org.bram.dtos.response.CreateTaskResponse;
import org.bram.dtos.response.LoginResponse;
import org.bram.dtos.response.RegisterUserResponse;
import org.bram.dtos.response.UpdateTaskResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TaskServicesImplTest {

    @Autowired
    private TaskServices taskServices;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServicesImpl userServices;
    private CreateTaskResponse createTaskResponse;
    private CreateTaskRequest createTaskRequest;
    private UpdateTaskResponse updateTaskResponse;
    private UpdateTaskRequest updateTaskRequest;
    private LoginResponse loginResponse;
    private LoginRequest loginRequest;
    private RegisterUserResponse registerUserResponse;


    @BeforeEach
    void setUp() {
        createTaskResponse = new CreateTaskResponse();
        createTaskRequest = new CreateTaskRequest();
        updateTaskResponse = new UpdateTaskResponse();
        updateTaskRequest = new UpdateTaskRequest();
        loginResponse = new LoginResponse();
        loginRequest = new LoginRequest();
        registerUserResponse = new RegisterUserResponse();
        taskRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    public void createTaskTest() {
        User user = registerUserAndLogin();
        createTaskRequest.setTitle("Groceries");
        createTaskRequest.setDescription("I am to get groceries on thursday evening");
        createTaskRequest.setUserId(user.getId());
        createTaskResponse = taskServices.createTask(createTaskRequest);

        assertNotNull(createTaskResponse.getTaskId());
        assertEquals("Groceries", createTaskResponse.getTitle());
        assertEquals("Pending...", createTaskResponse.getStatus().getStatus());
        assertEquals(1, taskRepository.count());

        Task savedTask = taskRepository.findById(createTaskResponse.getTaskId()).orElseThrow();
        assertEquals(user.getId(), savedTask.get);
    }

    @Test
    public void updateTaskTest() {
        createTaskTest();
        updateTaskRequest.setTaskId(createTaskResponse.getTaskId());
        updateTaskRequest.setDescription("I am to get groceries on saturday evening");
        updateTaskResponse = taskServices.updateTask(updateTaskRequest);
        assertEquals("Pending...", updateTaskResponse.getStatus().getStatus());
        assertEquals("Updated successfully", updateTaskResponse.getMessage());
    }

    @Test
    public void deleteTaskTest() {
        createTaskTest();
        CreateTaskRequest secondCreateTaskRequest = new CreateTaskRequest();
        secondCreateTaskRequest.setTitle("Electronics");
        secondCreateTaskRequest.setDescription("I am to get groceries on thursday evening");
        taskServices.createTask(secondCreateTaskRequest);
        DeleteTaskRequest deleteTaskRequest = new DeleteTaskRequest();
        deleteTaskRequest.setId(createTaskResponse.getTaskId());
        taskServices.deleteTask(deleteTaskRequest);
        assertEquals(1, taskRepository.count());
    }

    @Test
    public void markTaskAsCompletedTest() {
        createTaskTest();
        MarkTaskAsCompletedRequest markRequest = new MarkTaskAsCompletedRequest();
        markRequest.setTaskId(createTaskResponse.getTaskId());
        markRequest.setStatus(TaskStatus.COMPLETED);
        taskServices.markTaskAsCompleted(markRequest);
        Task task = taskRepository.findById(createTaskResponse.getTaskId()).get();
        assertEquals("Completed", task.getStatus().getStatus() );
    }

    @Test
    public void markTaskAsInProgressTest() {
        createTaskTest();
        MarkTaskAsInProgressRequest markRequest = new MarkTaskAsInProgressRequest();
        markRequest.setId(createTaskResponse.getTaskId());
        markRequest.setStatus(TaskStatus.IN_PROGRESS);
        taskServices.markTaskAsInProgress(markRequest);
        Task taskId = taskRepository.findById(createTaskResponse.getTaskId()).get();
        assertEquals("In Progress...", taskId.getStatus().getStatus());
    }

    private User registerUserAndLogin() {
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstName("Grace");
        registerRequest.setLastName("Ayoola");
        registerRequest.setEmail("grace@ayoola.com");
        registerUserResponse = userServices.registerUser(registerRequest);
        assertEquals("Registration Successful", registerUserResponse.getMessage());

        loginRequest.setEmail("grace@ayoola.com");
        loginRequest.setPassword("123456");
        loginResponse = userServices.login(loginRequest);
        assertEquals("Welcome back Grace Ayoola", loginResponse.getMessage());

        return userRepository.findById(loginResponse.getUserId()).orElseThrow();
    }
}
