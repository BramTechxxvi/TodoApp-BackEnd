package org.bram.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class CreateTaskRequest {

    @Id
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MMM-dd HH:mm")
    private String createdAt;
}
