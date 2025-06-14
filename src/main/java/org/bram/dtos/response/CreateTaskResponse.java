package org.bram.dtos.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CreateTaskResponse {

    private String status;
    private String createdAt;
    private String message;

}
