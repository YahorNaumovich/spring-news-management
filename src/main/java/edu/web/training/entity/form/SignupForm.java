package edu.web.training.entity.form;

import edu.web.training.validator.PasswordsMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordsMatch(message = "Password and Confirm Password must match")
public class SignupForm {

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    @NotEmpty(message = "Confirm Password cannot be empty")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String confirmPassword;

}
