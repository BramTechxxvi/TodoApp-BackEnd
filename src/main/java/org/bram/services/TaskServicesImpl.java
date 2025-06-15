package org.bram.services;

import org.bram.data.repositories.TaskRepository;
import org.bram.dtos.requests.CreateTaskRequest;
import org.bram.dtos.response.CreateTaskResponse;
import org.springframework.stereotype.Service;

@Service
public class TaskServicesImpl implements TaskServices {

    private TaskRepository taskRepository;

    public TaskServicesImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public CreateTaskResponse createTask(CreateTaskRequest request) {
        return null;
    }
}
