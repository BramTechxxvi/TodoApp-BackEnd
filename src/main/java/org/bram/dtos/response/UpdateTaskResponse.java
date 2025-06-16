package org.bram.dtos.response;

import lombok.Data;
import org.bram.data.models.TaskStatus;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class UpdateTaskResponse {

    @Id
    private String id;
    private LocalDateTime updatedAt;
    private  String message;
    private TaskStatus status;
}
