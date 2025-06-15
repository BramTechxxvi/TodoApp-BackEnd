package org.bram.services;

import org.bram.data.models.Task;
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
        Task newTask = new Task();
        newTask.setTitle(request.getTitle().trim());
        newTask.setDescription(request.getDescription());

        Task savedTask = taskRepository.save(newTask);
        CreateTaskResponse createTaskResponse = new CreateTaskResponse();
        createTaskResponse.setTitle(savedTask.getTitle());
        createTaskResponse.setMessage("Created successfully");

        return createTaskResponse;
    }
}
