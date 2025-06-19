package org.bram.dtos.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class RegisterUserResponse {

    @Id
    private String userId;
    private String fullName;
    private String message;
    private boolean success;
}
