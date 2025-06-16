package org.bram.services;

import org.bram.data.models.Task;
import org.bram.data.repositories.TaskRepository;
import org.bram.dtos.requests.CreateTaskRequest;
import org.bram.dtos.requests.DeleteTaskRequest;
import org.bram.dtos.requests.FindTasKRequest;
import org.bram.dtos.requests.UpdateTaskRequest;
import org.bram.dtos.response.CreateTaskResponse;
import org.bram.dtos.response.DeleteTaskResponse;
import org.bram.dtos.response.UpdateTaskResponse;
import org.springframework.stereotype.Service;

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
        Task taskToUpdate = existingTask.get();
        taskToUpdate.setDescription(request.getDescription());
        taskToUpdate.setUpdatedAt(request.getUpdatedAt());

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

    public Optional<Task> getTaskById(FindTasKRequest findTasKRequest) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) throw new RuntimeException("Task not found");

        return task;
    }


}
