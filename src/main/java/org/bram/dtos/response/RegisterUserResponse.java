package org.bram.dtos.response;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class RegisterUserResponse {

    @Id
    private String id;
    private String username;
    private String message;
}
