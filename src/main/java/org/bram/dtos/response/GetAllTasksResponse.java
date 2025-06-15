package org.bram.dtos.response;

import lombok.Data;
import org.bram.data.models.Task;

import java.util.List;

@Data
public class GetAllTasksResponse {

    private List<Task> tasks;
}
