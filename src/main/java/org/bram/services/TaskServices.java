package org.bram.services;

import org.bram.data.models.Task;
import org.bram.dtos.requests.CreateTaskRequest;
import org.bram.dtos.requests.DeleteTaskRequest;
import org.bram.dtos.requests.UpdateTaskRequest;
import org.bram.dtos.response.CreateTaskResponse;
import org.bram.dtos.response.DeleteTaskResponse;
import org.bram.dtos.response.UpdateTaskResponse;

import java.util.List;

public interface TaskServices {

    CreateTaskResponse createTask(CreateTaskRequest request);
    UpdateTaskResponse updateTask(UpdateTaskRequest request);
    List<Task> getAllTasks();
    DeleteTaskResponse deleteTask(DeleteTaskRequest request);

}
