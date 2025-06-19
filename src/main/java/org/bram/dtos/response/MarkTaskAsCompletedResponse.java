package org.bram.dtos.response;

import lombok.Data;

@Data
public class MarkTaskAsCompletedResponse {

    private String message;
    private boolean success;
}
