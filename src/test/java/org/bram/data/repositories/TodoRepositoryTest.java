package org.bram.data.repositories;

import org.bram.data.models.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        todoRepository.deleteAll();
    }

    @Test
    public void saveATodo__countIsOneTest() {
        Todo newTodo = new Todo();
        newTodo.setTitle("Buy groceries");
        newTodo.setDescription("I am to get groceries on thursday evening");
        Todo savedTodo = todoRepository.save(newTodo);
        assertNotNull(savedTodo.getId());
        assertEquals(1, todoRepository.count());
    }

}