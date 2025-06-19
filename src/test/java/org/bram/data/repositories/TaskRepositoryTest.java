package org.bram.data.repositories;

import org.bram.data.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
    }

    @Test
    public void saveATodo__countIsOneTest() {
        Task newTask = new Task();
        newTask.setTitle("Buy groceries");
        newTask.setDescription("I am to get groceries on thursday evening");
        Task savedTask = taskRepository.save(newTask);
        assertNotNull(savedTask.getId());
        assertEquals(1, taskRepository.count());
    }
}