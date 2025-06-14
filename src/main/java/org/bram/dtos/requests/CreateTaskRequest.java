package org.bram.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateTaskRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private String dueDate;
    private String status;
    private String createdAt;
}
