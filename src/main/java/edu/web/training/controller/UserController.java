package edu.web.training.controller;

import edu.web.training.entity.UserRole;
import edu.web.training.entity.form.LoginForm;
import edu.web.training.entity.User;
import edu.web.training.entity.form.SignupForm;
import edu.web.training.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final String LOGIN_PAGE = "login";
    private static final String SIGNUP_PAGE = "signup";
    private static final String REDIRECT_HOME = "redirect:/";
    private static final String ERROR_ATTRIBUTE = "error";
    private static final String LOGIN_FORM_ATTRIBUTE = "loginForm";
    private static final String SIGNUP_FORM_ATTRIBUTE = "signupForm";
    private static final String USER_ATTRIBUTE = "user";
    private static final String DEFAULT_ROLE_NAME = "viewer";
    private static final int DEFAULT_ROLE_ID = 3;
    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/login")
    public String goToLoginPage(Model model) {

        model.addAttribute(LOGIN_FORM_ATTRIBUTE, new LoginForm());

        return LOGIN_PAGE;
    }

    @RequestMapping("/signup")
    public String goToSignUpPage(Model model) {

        model.addAttribute(SIGNUP_FORM_ATTRIBUTE, new SignupForm());

        return SIGNUP_PAGE;
    }

    @RequestMapping("/create")
    public String createUser(@Valid @ModelAttribute(SIGNUP_FORM_ATTRIBUTE) SignupForm signupForm, BindingResult bindingResult, Model model, Locale locale) {

        if (bindingResult.hasErrors()) {
            return SIGNUP_PAGE;
        }

        boolean usernameExists = userService.usernameExists(signupForm.getUsername());
        boolean emailExists = userService.emailExists(signupForm.getEmail());

        if (usernameExists) {
            model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.username.taken", null, locale));
            return SIGNUP_PAGE;
        }

        if (emailExists) {
            model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.email.registered", null, locale));
            return SIGNUP_PAGE;
        }

        // Create a new user
        User newUser = new User();
        newUser.setUsername(signupForm.getUsername());
        newUser.setEmail(signupForm.getEmail());
        newUser.setPassword(signupForm.getPassword());
        UserRole defaultRole = new UserRole();
        defaultRole.setId(DEFAULT_ROLE_ID);
        defaultRole.setName(DEFAULT_ROLE_NAME);
        newUser.setUserRole(defaultRole);

        userService.createUser(newUser);

        return REDIRECT_HOME;
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@Valid @ModelAttribute(LOGIN_FORM_ATTRIBUTE) LoginForm loginForm, BindingResult bindingResult, Model model, HttpSession session, Locale locale) {

        if (bindingResult.hasErrors()) {
            return LOGIN_PAGE;
        }

        User user = userService.authenticate(loginForm.getUsername(), loginForm.getPassword());

        if (user == null) {

            model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.invalid.credentials", null, locale));

            return LOGIN_PAGE;
        }

        session.setAttribute(USER_ATTRIBUTE, user);

        return REDIRECT_HOME;

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return REDIRECT_HOME;
    }
}
