package edu.web.training.controller;

import edu.web.training.entity.UserRole;
import edu.web.training.entity.form.LoginForm;
import edu.web.training.entity.User;
import edu.web.training.entity.form.SignupForm;
import edu.web.training.exception.ServiceException;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final String REDIRECT_HOME = "redirect:/";
    private static final String REDIRECT_USER_MANAGEMENT = "redirect:/user/manage";
    private static final String REDIRECT_LOGIN = "redirect:/user/login";
    private static final String LOGIN_PAGE = "login";
    private static final String SIGNUP_PAGE = "signup";
    private static final String PROFILE_PAGE = "profile";
    private static final String USER_MANAGEMENT_PAGE = "user-management";
    private static final String ERROR_ATTRIBUTE = "error";
    private static final String USER_ATTRIBUTE = "user";
    private static final String USERS_ATTRIBUTE = "users";
    private static final String ROLES_ATTRIBUTE = "roles";
    private static final String LOGIN_FORM_ATTRIBUTE = "loginForm";
    private static final String SIGNUP_FORM_ATTRIBUTE = "signupForm";
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

    @RequestMapping("/profile")
    public String goToProfilePage(Model model, HttpSession session) {

        User user = (User) session.getAttribute(USER_ATTRIBUTE);

        if (user == null) {
            return REDIRECT_LOGIN;
        }

        model.addAttribute(USER_ATTRIBUTE, user);
        return PROFILE_PAGE;

    }

    @RequestMapping("/manage")
    public String goToUserManagementPage(Model model, Locale locale, HttpSession session) {

        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        if (user == null || !user.getUserRole().getName().equals("Admin")) {
            return REDIRECT_HOME;
        }

        try {
            List<User> users = userService.getAllUsers();
            model.addAttribute(USERS_ATTRIBUTE, users);

            List<UserRole> roles = userService.getAllRoles();
            model.addAttribute(ROLES_ATTRIBUTE, roles);

            return USER_MANAGEMENT_PAGE;
        } catch (ServiceException e) {
            model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.user.user-management", null, locale));
            return USER_MANAGEMENT_PAGE;
        }
    }

    @RequestMapping("/create")
    public String createUser(@Valid @ModelAttribute(SIGNUP_FORM_ATTRIBUTE) SignupForm signupForm, BindingResult bindingResult, Model model, Locale locale) {
        if (bindingResult.hasErrors()) {
            return SIGNUP_PAGE;
        }

        try {
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
            UserRole defaultRole = new UserRole(DEFAULT_ROLE_ID, DEFAULT_ROLE_NAME);
            newUser.setUserRole(defaultRole);

            userService.createUser(newUser);

            return REDIRECT_HOME;
        } catch (ServiceException e) {
            model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.user.create", null, locale));
            return SIGNUP_PAGE;
        }
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@Valid @ModelAttribute(LOGIN_FORM_ATTRIBUTE) LoginForm loginForm, BindingResult bindingResult, Model model, HttpSession session, Locale locale) {
        if (bindingResult.hasErrors()) {
            return LOGIN_PAGE;
        }

        try {
            User user = userService.authenticate(loginForm.getUsername(), loginForm.getPassword());

            if (user == null) {
                model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.invalid.credentials", null, locale));
                return LOGIN_PAGE;
            }

            session.setAttribute(USER_ATTRIBUTE, user);
            return REDIRECT_HOME;
        } catch (ServiceException e) {
            model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.user.authenticate", null, locale));
            return LOGIN_PAGE;
        }
    }

    @RequestMapping("/update-role")
    public String updateUserRole(@RequestParam("id") int userId, @RequestParam("roleId") int roleId, Model model, Locale locale) {
        try {
            userService.updateUserRole(userId, roleId);
            return REDIRECT_USER_MANAGEMENT;
        } catch (ServiceException e) {
            model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.user.update-role", null, locale));
            return USER_MANAGEMENT_PAGE;
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return REDIRECT_HOME;
    }
}
