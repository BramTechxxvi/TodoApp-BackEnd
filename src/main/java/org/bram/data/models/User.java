package org.bram.data.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection= "Users")
public class User {

    @Id
    private String id;
    @NotBlank(message = "Enter your first name")
    private String firstName;
    @NotBlank(message = "Enter your last name")
    private String lastName;
    @NotBlank(message = "Enter a valid Email")
    @Pattern(regexp= "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format" )
    private String email;
    @NotBlank(message = "Enter a password")
    @Size(min= 8, max=16, message = "Password must be between 8 to 16 characters")
    private String password;

}
