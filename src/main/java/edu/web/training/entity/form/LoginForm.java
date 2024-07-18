package edu.web.training.entity.form;

import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

}
