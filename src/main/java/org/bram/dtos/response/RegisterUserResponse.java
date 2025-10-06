package org.bram.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterUserResponse {

    @Id
    private String userId;
    private String fullName;
    private String message;
    private boolean success;
}
