package org.bram.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequest {

    @NotBlank(message = "Enter old password")
    @Size(min = 8, max= 16, message = "Password must be between 8 to 16 characters")
    private String oldPassword;
    @NotBlank(message = "Enter new password")
    @Size(min = 8, max= 16, message = "Password must be between 8 to 16 characters")
    private String newPassword;
}
