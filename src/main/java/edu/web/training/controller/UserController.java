package edu.web.training.controller;

import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import edu.web.training.entity.LoginForm;
import edu.web.training.entity.User;
import edu.web.training.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String goToSignUpPage() {
        return "signup";
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@ModelAttribute("loginForm") LoginForm loginForm, Model model, HttpSession session) {
        User user = userService.authenticate(loginForm.getUsername(), loginForm.getPassword());
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
