package org.bram.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class FindTasKRequest {
    @Id
    private String taskId;
}
