package org.bram.dtos.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CreateTaskResponse {

    @Id
    private String taskId;
    @NotBlank(message = "Task must have a title")
    private String title;
    private String createdAt;
    private String message;

}
