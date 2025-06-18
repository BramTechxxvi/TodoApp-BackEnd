package org.bram.controllers;

import jakarta.validation.Valid;
import org.bram.data.models.Task;
import org.bram.dtos.requests.*;
import org.bram.dtos.response.*;
import org.bram.services.TaskServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping ("/tasks")
//@CrossOrigin(origins = "*" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class TaskController {

    private TaskServices taskServices;

    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody @Valid CreateTaskRequest createTaskRequest) {
        try {
            CreateTaskResponse response = taskServices.createTask(createTaskRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Failed to create task" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateTaskResponse> updateTask(@PathVariable String id, @RequestBody UpdateTaskRequest request) {
        request.setTaskId(id);
        UpdateTaskResponse response = taskServices.updateTask(request);
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

    @PatchMapping("/{id}/in-progress")
    public ResponseEntity<MarkTaskAsInProgressResponse> markTaskAsInProgress(@PathVariable String id) {
        MarkTaskAsInProgressRequest request = new MarkTaskAsInProgressRequest();
        request.setId(id);
        MarkTaskAsInProgressResponse response = taskServices.markTaskAsInProgress(request);
        return ResponseEntity.ok(response);
    }

}