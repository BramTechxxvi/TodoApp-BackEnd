package org.bram.dtos.requests;

import lombok.Data;
import org.bram.data.models.Task;

import java.util.List;

@Data
public class GetAllTasksRequest {

    private List<Task> tasks;
}
