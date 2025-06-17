package org.bram.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ChangeEmailRequest {

    @NotBlank(message = "Enter your old email")
    @Pattern(regexp= "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format" )
    private String oldEmail;
    @NotBlank(message = "Enter your new email")
    @Pattern(regexp= "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format" )
    private String newEmail;
}
