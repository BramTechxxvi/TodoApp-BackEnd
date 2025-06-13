package org.bram.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class RegisterUserRequest {

    @Id
    private String id;
    private String username;
    private String password;
}
