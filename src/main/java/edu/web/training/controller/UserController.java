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
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

    private static final String REDIRECT_NEWS = "redirect:/news";

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/login")
    public String goToLoginPage(Model model) {

        model.addAttribute(LOGIN_FORM_ATTRIBUTE, new LoginForm());

        return LOGIN_PAGE;
    }

    @RequestMapping("/authenticate")
    public String authenticate(Authentication authentication, HttpSession session) {

        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        boolean userExists = userService.emailExists(oauth2User.getAttribute("email"));

        User user;

        if (!userExists) {
            user = new User();
            user.setUsername(oauth2User.getAttribute("name"));
            user.setEmail(oauth2User.getAttribute("email"));
            user.setUserRole(new UserRole(DEFAULT_ROLE_ID, DEFAULT_ROLE_NAME));
            userService.createUser(user);
        } else {
            user = userService.findByEmail(oauth2User.getAttribute("email"));
        }

        session.setAttribute(USER_ATTRIBUTE, user);

        return REDIRECT_NEWS;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return REDIRECT_HOME;
    }
}
