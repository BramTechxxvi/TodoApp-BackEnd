package org.bram.dtos.response;

import lombok.Data;
import org.bram.data.models.Task;

@Data
public class FindTasKResponse {

    private Task task;
}
