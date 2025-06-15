package org.bram.dtos.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateTaskResponse {

    private LocalDateTime updatedAt;
    private  String message;
}
