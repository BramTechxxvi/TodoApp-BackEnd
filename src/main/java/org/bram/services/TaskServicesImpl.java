package org.bram.services;

import org.bram.data.models.Task;
import org.bram.data.models.TaskStatus;
import org.bram.data.repositories.TaskRepository;
import org.bram.dtos.requests.*;
import org.bram.dtos.response.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        createTaskResponse.setTaskId(savedTask.getId());
        createTaskResponse.setTitle(savedTask.getTitle());
        createTaskResponse.setMessage("Created successfully");

        return createTaskResponse;
    }

    @Override
    public UpdateTaskResponse updateTask(UpdateTaskRequest request) {
        Optional<Task> existingTask = taskRepository.findById(request.getTaskId());
        if (existingTask.isEmpty()) throw RuntimeException("Task not found");

        Task taskToUpdate = existingTask.get();
        taskToUpdate.setDescription(request.getDescription());
        taskToUpdate.setUpdatedAt(LocalDateTime.now());

        Task updatedTask = taskRepository.save(taskToUpdate);

        UpdateTaskResponse updateTaskResponse = new UpdateTaskResponse();
        updateTaskResponse.setMessage("Updated successfully");
        updateTaskResponse.setUpdatedAt(updatedTask.getUpdatedAt());
        updateTaskResponse.setStatus(updatedTask.getStatus());

        return updateTaskResponse;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public DeleteTaskResponse deleteTask(DeleteTaskRequest request) {
        taskRepository.deleteById(request.getId());
        DeleteTaskResponse response = new DeleteTaskResponse();
        response.setMessage("Deleted successfully");
        return response;
    }

    @Override
    public FindTasKResponse getTaskById(FindTasKRequest findTasKRequest) {
        Optional<Task> task = taskRepository.findById(findTasKRequest.getTaskId());
        if (task.isEmpty()) throw new RuntimeException("Task not found");

        FindTasKResponse findTasKResponse = new FindTasKResponse();
        findTasKResponse.setTask(task.get());
        return findTasKResponse;
    }

    @Override
    public MarkTaskAsCompletedResponse markTaskAsCompleted(MarkTaskAsCompletedRequest request) {
        Optional<Task> task = taskRepository.findById(request.getTaskId());
        if (task.isEmpty()) throw new RuntimeException("Task not found");

        Task newTask = task.get();
        newTask.setStatus(TaskStatus.COMPLETED);
        taskRepository.save(newTask);

        MarkTaskAsCompletedResponse markTaskAsCompletedResponse = new MarkTaskAsCompletedResponse();
        markTaskAsCompletedResponse.setMessage("Success");
        return markTaskAsCompletedResponse;
    }


}
