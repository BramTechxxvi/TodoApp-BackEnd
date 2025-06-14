package org.bram.dtos.requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequest {

    @NotBlank(message = "Can't leave field empty")
    @Size(min= 2, message = "Enter a valid username")
    private String username;
    @NotBlank(message = "Can't leave field empty")
    @Size(min= 8, max= 16, message = "Password must be between 8 and 16 characters")
    private String password;
}

