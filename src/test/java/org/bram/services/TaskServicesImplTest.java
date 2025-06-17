package org.bram.services;

import org.bram.data.models.Task;
import org.bram.data.models.TaskStatus;
import org.bram.data.repositories.TaskRepository;
import org.bram.dtos.requests.*;
import org.bram.dtos.response.CreateTaskResponse;
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

    private CreateTaskResponse createTaskResponse;
    private CreateTaskRequest createTaskRequest;
    private UpdateTaskResponse updateTaskResponse;
    private UpdateTaskRequest updateTaskRequest;

    @BeforeEach
    void setUp() {
        createTaskResponse = new CreateTaskResponse();
        createTaskRequest = new CreateTaskRequest();
        updateTaskResponse = new UpdateTaskResponse();
        updateTaskRequest = new UpdateTaskRequest();
        taskRepository.deleteAll();
    }


    @Test
    public void createTaskTest() {
        createTaskRequest.setTitle("Groceries");
        createTaskRequest.setDescription("I am to get groceries on thursday evening");
        createTaskResponse = taskServices.createTask(createTaskRequest);
        assertNotNull(createTaskResponse.getTitle());
        assertEquals(1, taskRepository.count());
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
}
