package org.bram.services;

import org.bram.dtos.requests.CreateTaskRequest;
import org.bram.dtos.response.CreateTaskResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TaskServicesImplTest {

    private CreateTaskResponse createTaskResponse;
    private CreateTaskRequest createTaskRequest;

    @BeforeEach
    void setUp() {
        createTaskResponse = new CreateTaskResponse();
        createTaskRequest = new CreateTaskRequest();

    }


    @Test
    public void testCreateTask() {
        createTaskRequest.setTitle("Groceries");
        createTaskRequest.setDescription("I am to get groceries on thursday evening");
        createTaskRequest.se;

    }
  
}