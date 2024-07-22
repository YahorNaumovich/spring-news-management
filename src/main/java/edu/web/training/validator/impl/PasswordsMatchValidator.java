package edu.web.training.validator.impl;

import edu.web.training.entity.form.SignupForm;
import edu.web.training.validator.PasswordsMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, SignupForm> {

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(SignupForm signupForm, ConstraintValidatorContext context) {

        if (signupForm == null) {
            return true;
        }

        boolean isValid = signupForm.getPassword() != null && signupForm.getPassword().equals(signupForm.getConfirmPassword());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
