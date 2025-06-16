package org.bram.controllers;

import org.bram.data.models.Task;
import org.bram.dtos.requests.*;
import org.bram.dtos.response.*;
import org.bram.services.TaskServices;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/tasks")
public class TaskController {

    private TaskServices taskServices;

    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @PostMapping
    public ResponseEntity<CreateTaskResponse> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        CreateTaskResponse response = taskServices.createTask(createTaskRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<UpdateTaskResponse> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) {
        UpdateTaskResponse response = taskServices.updateTask(updateTaskRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(taskServices.getAllTasks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteTaskResponse> deleteTask(@PathVariable String id) {
        DeleteTaskRequest request = new DeleteTaskRequest();
        request.setId(id);
        DeleteTaskResponse response = taskServices.deleteTask(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindTasKResponse> getTaskById(@PathVariable String id) {
        FindTasKRequest request = new FindTasKRequest();
        request.setTaskId(id);
        FindTasKResponse response = taskServices.getTaskById(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<MarkTaskAsCompletedResponse> markTaskAsCompleted(@PathVariable String id) {
        MarkTaskAsCompletedRequest request = new MarkTaskAsCompletedRequest();
        request.setTaskId(id);
        MarkTaskAsCompletedResponse response = taskServices.markTaskAsCompleted(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/in_progress")
    public ResponseEntity<MarkTaskAsInProgressResponse> markTaskAsInProgress(@PathVariable String id) {
        MarkTaskAsInProgressRequest request = new MarkTaskAsInProgressRequest();
        request.setId(id);
        MarkTaskAsInProgressResponse response = taskServices.markTaskInProgress(request);
        return ResponseEntity.ok(response);
    }

}