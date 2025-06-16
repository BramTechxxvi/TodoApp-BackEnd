package org.bram.dtos.requests;

import lombok.Data;
import org.bram.data.models.TaskStatus;
import org.springframework.data.annotation.Id;

import java.lang.classfile.instruction.TableSwitchInstruction;

@Data
public class MarkTaskAsInProgressRequest {

    @Id
    private String id;
    private TaskStatus status;
}
