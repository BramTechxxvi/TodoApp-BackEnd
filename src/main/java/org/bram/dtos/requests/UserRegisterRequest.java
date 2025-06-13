package org.bram.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserRegisterRequest {

    @Id
    private String id;
    @NotBlank(message = "Can't leave field empty")
    @Email(message = "Enter a valid email")
    private String email;
    @NotBlank(message = "Can't leave field empty")
    @Size(min= 2, message = "Enter aa valid email")
    private String username;
    @NotBlank
    @Size(min= 8, max= 16, message = "Password must be between 8 and 16 characters")
    private String password;
}
