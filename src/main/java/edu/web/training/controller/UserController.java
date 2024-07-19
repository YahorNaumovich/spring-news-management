package edu.web.training.controller;

import edu.web.training.entity.UserRole;
import edu.web.training.entity.form.LoginForm;
import edu.web.training.entity.User;
import edu.web.training.entity.form.SignupForm;
import edu.web.training.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String goToLoginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @RequestMapping("/signup")
    public String goToSignUpPage(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "signup";
    }

    @RequestMapping("/create")
    public String createUser(@Valid @ModelAttribute("signupForm") SignupForm signupForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        boolean usernameExists = userService.usernameExists(signupForm.getUsername());
        boolean emailExists = userService.emailExists(signupForm.getEmail());

        if (usernameExists) {
            model.addAttribute("error", "Username is already taken");
            return "signup";
        }

        if (emailExists) {
            model.addAttribute("error", "Email is already registered");
            return "signup";
        }

        // Create a new user
        User newUser = new User();
        newUser.setUsername(signupForm.getUsername());
        newUser.setEmail(signupForm.getEmail());
        newUser.setPassword(signupForm.getPassword());
        UserRole defaultRole = new UserRole();
        defaultRole.setId(3);
        defaultRole.setName("viewer");
        newUser.setUserRole(defaultRole);

        userService.createUser(newUser);

        return "redirect:/";
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        User user = userService.authenticate(loginForm.getUsername(), loginForm.getPassword());
        if (user == null) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
        session.setAttribute("user", user);
        return "redirect:/";

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
