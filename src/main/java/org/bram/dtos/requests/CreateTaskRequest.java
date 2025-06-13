package org.bram.dtos.requests;

import lombok.Data;

@Data
public class CreateTaskRequest {

    private String title;
    private String description;
    private String dueDate;
    private String status;
    private String createdAt;
    private String updatedAt;
}
