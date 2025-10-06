package org.bram.utils.Mapper;

import org.bram.data.models.Task;
import org.bram.data.models.TaskStatus;
import org.bram.data.models.User;
import org.bram.dtos.requests.CreateTaskRequest;

import java.time.LocalDateTime;

public class TaskMapper {

    public static Task map(User user, CreateTaskRequest request) {
        Task newTask = new Task();
        newTask.setUser(user);
        newTask.setTitle(request.getTitle());
        newTask.setDescription(request.getDescription());
        newTask.setCreatedAt(LocalDateTime.now());
        newTask.setStatus(TaskStatus.PENDING);
        return newTask;
    }
}
