package edu.web.training.entity.form;

import edu.web.training.validator.PasswordsMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordsMatch(message = "{signup-form.passwords.match}")
public class SignupForm {

    @NotEmpty(message = "{signup-form.username.required}")
    @Size(min = 3, max = 20, message = "{signup-form.username.size}")
    private String username;

    @NotEmpty(message = "{signup-form.email.required}")
    @Email(message = "{signup-form.email.invalid}")
    private String email;

    @NotEmpty(message = "{signup-form.password.required}")
    @Size(min = 6, max = 20, message = "{signup-form.password.size}")
    private String password;

    @NotEmpty(message = "{signup-form.confirmPassword.required}")
    @Size(min = 6, max = 20, message = "{signup-form.password.size}")
    private String confirmPassword;

}
