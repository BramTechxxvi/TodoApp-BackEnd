package org.bram.services;

import org.bram.data.models.Task;
import org.bram.dtos.requests.*;
import org.bram.dtos.response.*;

import java.util.List;

public interface TaskServices {

    CreateTaskResponse createTask(CreateTaskRequest request);
    UpdateTaskResponse updateTask(UpdateTaskRequest request);
    List<Task> getAllTasks();
    DeleteTaskResponse deleteTask(DeleteTaskRequest request);

    FindTasKResponse getTaskById(FindTasKRequest findTasKRequest);

    MarkTaskAsCompletedResponse markTaskAsCompleted(MarkTaskAsCompletedRequest request);

    MarkTaskAsInProgressResponse markTaskInProgress(MarkTaskAsInProgressRequest request);

}
