package org.bram.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bram.data.models.TaskStatus;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateTaskResponse {

    @Id
    private String taskId;
    private String title;
    @DateTimeFormat(pattern = "yyy-MMM-dd HH:mm")
    private LocalDateTime createdAt;
    private TaskStatus status;
    private String message;
    private boolean success;

}
