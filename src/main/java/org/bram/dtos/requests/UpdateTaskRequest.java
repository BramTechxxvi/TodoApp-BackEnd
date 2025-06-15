package org.bram.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class UpdateTaskRequest {

    @Id
    private String taskId;
    private String description;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MMM-dd HH:mm")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
