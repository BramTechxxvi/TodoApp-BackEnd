package org.bram.services;

import org.bram.data.repositories.TaskRepository;
import org.bram.dtos.requests.CreateTaskRequest;
import org.bram.dtos.response.CreateTaskResponse;
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

    private CreateTaskResponse createTaskResponse;
    private CreateTaskRequest createTaskRequest;

    @BeforeEach
    void setUp() {
        createTaskResponse = new CreateTaskResponse();
        createTaskRequest = new CreateTaskRequest();
        taskRepository.deleteAll();
    }


    @Test
    public void testCreateTask() {
        createTaskRequest.setTitle("Groceries");
        createTaskRequest.setDescription("I am to get groceries on thursday evening");
        createTaskResponse = taskServices.createTask(createTaskRequest);
        assertNotNull(createTaskResponse.getTitle());
        assertEquals(1, taskRepository.count());
    }

    @Test
    public void 
  
}