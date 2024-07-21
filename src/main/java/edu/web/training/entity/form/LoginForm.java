package edu.web.training.entity.form;

import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty(message = "{login-form.username.required}")
    @Size(min = 3, max = 20, message = "{login-form.username.size}")
    private String username;

    @NotEmpty(message = "{login-form.password.required}")
    @Size(min = 6, max = 20, message = "{login-form.password.size}")
    private String password;

}
