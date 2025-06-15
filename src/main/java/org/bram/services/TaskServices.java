package org.bram.services;

import org.bram.dtos.requests.CreateTaskRequest;
import org.bram.dtos.response.CreateTaskResponse;

public interface TaskServices {

    CreateTaskResponse createTask(CreateTaskRequest request);


}
