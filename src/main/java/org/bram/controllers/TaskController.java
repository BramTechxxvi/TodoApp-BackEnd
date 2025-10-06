package org.bram.controllers;

import jakarta.validation.Valid;
import org.bram.data.models.Task;
import org.bram.dtos.requests.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.TaskNotFoundException;
import org.bram.services.TaskServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping ("/tasks")
public class TaskController {

    private final TaskServicesImpl taskServices;

    @Autowired
    public TaskController(TaskServicesImpl taskServices) {
        this.taskServices = taskServices;
    }

    @PostMapping("/add")
    public ResponseEntity<CreateTaskResponse> createTask(@RequestBody @Valid CreateTaskRequest createTaskRequest) {
        try {
            CreateTaskResponse response = taskServices.createTask(createTaskRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

            public ResponseEntity<CreateTaskResponse> createTask(
                    @RequestBody CreateTaskRequest request,
                    @RequestHeader(value = "X-User-Id", required = false) String userId) {

                if (userId == null) {
                    userId = "66f987bd71f6c9123456789a"; // fallback to default user
                }
                request.setUserId(userId);
                return ResponseEntity.ok(taskService.createTask(request));
            }








        } catch (IllegalArgumentException e) {
            CreateTaskResponse response = new CreateTaskResponse();
            response.setMessage("Invalid input " + e.getMessage());
            response.setSuccess(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (RuntimeException e) {
            CreateTaskResponse response = new CreateTaskResponse();
            response.setMessage("Task could not be created " + e.getMessage());
            response.setSuccess(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateTaskResponse> updateTask(@PathVariable("id") String id, @RequestBody UpdateTaskRequest request) {
        try {
            request.setTaskId(id);
            UpdateTaskResponse response = taskServices.updateTask(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (IllegalArgumentException e) {
            UpdateTaskResponse errorResponse = new UpdateTaskResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Invalid input" + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

        } catch (TaskNotFoundException e) {
            UpdateTaskResponse errorResponse = new UpdateTaskResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/getAll/{id}")
    public ResponseEntity<List<Task>> getAllUserTasks(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskServices.getAllTasks(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteTaskResponse> deleteTask(@PathVariable("id") String id, @RequestBody DeleteTaskRequest request) {
        try {
            request.setId(id);
            DeleteTaskResponse response = taskServices.deleteTask(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch(TaskNotFoundException e) {
            DeleteTaskResponse errorResponse = new DeleteTaskResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/getTask/{id}")
    public ResponseEntity<FindTaskResponse> getTaskById(@PathVariable("id") String id, @RequestBody FindTaskRequest request) {
        try {
            request.setTaskId(id);
            FindTaskResponse response = taskServices.getTaskById(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (TaskNotFoundException e) {
            FindTaskResponse response = new FindTaskResponse();
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<MarkTaskAsCompletedResponse> markTaskAsCompleted(@PathVariable("id") String id, @RequestBody MarkTaskAsCompletedRequest request) {
        try {
            request.setTaskId(id);
            MarkTaskAsCompletedResponse response = taskServices.markTaskAsCompleted(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch(TaskNotFoundException e) {
            MarkTaskAsCompletedResponse response = new MarkTaskAsCompletedResponse();
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PatchMapping("/{id}/in-progress")
    public ResponseEntity<MarkTaskAsInProgressResponse> markTaskAsInProgress(@PathVariable("id") String id, @RequestBody MarkTaskAsInProgressRequest request) {
        try {
            request.setId(id);
            MarkTaskAsInProgressResponse response = taskServices.markTaskAsInProgress(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch(TaskNotFoundException e) {
            MarkTaskAsInProgressResponse response = new MarkTaskAsInProgressResponse();
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}