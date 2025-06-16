package org.bram.dtos.requests;

import lombok.Data;
import org.bram.data.models.TaskStatus;
import org.springframework.data.annotation.Id;

@Data
public class MarkTaskAsCompletedRequest {
    @Id
    private String taskId;
    private TaskStatus status;
}
