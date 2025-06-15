package org.bram.dtos.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class UpdateTaskResponse {

    @Id
    private String id;
    private LocalDateTime updatedAt;
    private  String message;
}
