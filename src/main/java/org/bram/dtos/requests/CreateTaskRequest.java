package org.bram.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.bram.data.models.TaskStatus;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class CreateTaskRequest {

    @Id
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private TaskStatus status;
    @DateTimeFormat(pattern = "yyyy-MMM-dd HH:mm")
    private LocalDateTime createdAt;
}
