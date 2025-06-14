package org.bram.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CreateTaskRequest {

    @Id
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private String dueDate;
    private String status;
    private String createdAt;
}
