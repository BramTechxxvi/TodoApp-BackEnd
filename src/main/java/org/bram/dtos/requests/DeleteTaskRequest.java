package org.bram.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class DeleteTaskRequest {

    @Id
    private String id;
}
