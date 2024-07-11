package edu.web.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @RequestMapping("/page")
    public String signup() {
        return "signup";
    }

}
