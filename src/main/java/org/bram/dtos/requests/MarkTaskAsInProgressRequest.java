package org.bram.dtos.requests;

import lombok.Data;
import org.bram.data.models.TaskStatus;
import org.springframework.data.annotation.Id;

@Data
public class MarkTaskAsInProgressRequest {

    @Id
    private String id;
    private TaskStatus status;
}
