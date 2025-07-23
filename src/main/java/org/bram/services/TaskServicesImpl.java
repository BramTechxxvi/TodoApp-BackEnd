package org.bram.services;

import org.bram.data.models.Task;
import org.bram.data.models.TaskStatus;
import org.bram.data.repositories.TaskRepository;
import org.bram.dtos.requests.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServicesImpl implements TaskServices {

    private TaskRepository taskRepository;

    @Autowired
    public TaskServicesImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public CreateTaskResponse createTask(CreateTaskRequest request) {
        Task newTask = new Task();
        newTask.setTitle(request.getTitle());
        newTask.setDescription(request.getDescription());
        newTask.setCreatedAt(LocalDateTime.now());
        newTask.setStatus(TaskStatus.PENDING);

        Task savedTask = taskRepository.save(newTask);
        CreateTaskResponse createTaskResponse = new CreateTaskResponse();
        createTaskResponse.setTaskId(savedTask.getId());
        createTaskResponse.setTitle(savedTask.getTitle());
        createTaskResponse.setCreatedAt(savedTask.getCreatedAt());
        createTaskResponse.setStatus(savedTask.getStatus());
        createTaskResponse.setSuccess(true);
        createTaskResponse.setMessage("Created successfully");

        return createTaskResponse;
    }

    @Override
    public UpdateTaskResponse updateTask(UpdateTaskRequest request) {
        Optional<Task> existingTask = taskRepository.findById(request.getTaskId());
        if (existingTask.isEmpty()) throw new TaskNotFoundException("Task not found");

        Task taskToUpdate = existingTask.get();
        boolean validTitle = request.getTitle() != null && !request.getTitle().trim().isEmpty();
        if (validTitle) taskToUpdate.setTitle(request.getTitle().trim());
        taskToUpdate.setDescription(request.getDescription());
        taskToUpdate.setUpdatedAt(LocalDateTime.now());

        Task updatedTask = taskRepository.save(taskToUpdate);

        UpdateTaskResponse updateTaskResponse = new UpdateTaskResponse();
        updateTaskResponse.setMessage("Updated successfully");
        updateTaskResponse.setUpdatedAt(updatedTask.getUpdatedAt());
        updateTaskResponse.setStatus(updatedTask.getStatus());
        updateTaskResponse.setSuccess(true);

        return updateTaskResponse;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public DeleteTaskResponse deleteTask(DeleteTaskRequest request) {
        Optional<Task> task = taskRepository.findById(request.getId());
        if(task.isEmpty()) throw new TaskNotFoundException("Task not found");

        taskRepository.deleteById(request.getId());
        DeleteTaskResponse response = new DeleteTaskResponse();
        response.setSuccess(true);
        response.setMessage("Deleted successfully");
        return response;
    }

    @Override
    public FindTaskResponse getTaskById(FindTaskRequest findTasKRequest) {
        Optional<Task> task = taskRepository.findById(findTasKRequest.getTaskId());
        if (task.isEmpty()) throw new TaskNotFoundException("Task not found");

        FindTaskResponse findTasKResponse = new FindTaskResponse();
        findTasKResponse.setTask(task.get());
        findTasKResponse.setSuccess(true);
        return findTasKResponse;
    }

    @Override
    public MarkTaskAsCompletedResponse markTaskAsCompleted(MarkTaskAsCompletedRequest request) {
        Optional<Task> task = taskRepository.findById(request.getTaskId());
        if (task.isEmpty()) throw new TaskNotFoundException("Task not found");

        Task newTask = task.get();
        newTask.setStatus(TaskStatus.COMPLETED);
        taskRepository.save(newTask);

        MarkTaskAsCompletedResponse markTaskAsCompletedResponse = new MarkTaskAsCompletedResponse();
        markTaskAsCompletedResponse.setMessage("Marked as completed");
        markTaskAsCompletedResponse.setSuccess(true);
        return markTaskAsCompletedResponse;
    }

    @Override
    public MarkTaskAsInProgressResponse markTaskAsInProgress(MarkTaskAsInProgressRequest request) {
        Optional<Task> task = taskRepository.findById(request.getId());
        if (task.isEmpty()) throw new RuntimeException("Task not found");

        Task newTask = task.get();
        newTask.setStatus(TaskStatus.IN_PROGRESS);
        taskRepository.save(newTask);

        MarkTaskAsInProgressResponse markResponse = new MarkTaskAsInProgressResponse();
        markResponse.setMessage("Marked as in progress");
        markResponse.setSuccess(true);
        return markResponse;
    }
}
