package org.bram.dtos.response;

import lombok.Data;
import org.bram.data.models.Task;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class FindTasKResponse {

    @DBRef
    private Task task;
}
