package org.bram.dtos.response;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class CreateTaskResponse {

    @Id
    private String taskId;
    private String title;
    @DateTimeFormat(pattern = "yyy-MMM-dd HH:mm")
    private LocalDateTime createdAt;
    private String message;
    private boolean success;

}
