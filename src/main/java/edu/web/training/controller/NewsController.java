package edu.web.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewsController {

    @RequestMapping("/")
    public  String redirectToMainPage() {
        return "redirect:/news";
    }

    @RequestMapping("/news")
    public String news() {
        return "news";
    }

    @RequestMapping("/article")
    public String article() {
        return "article";
    }

}
