package edu.web.training.controller;

import edu.web.training.entity.Article;
import edu.web.training.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/")
    public  String redirectToMainPage() {
        return "redirect:/news";
    }

    @RequestMapping("/news")
    public String news(Model model) {
        List<Article> articles = newsService.getAllArticles();
        model.addAttribute("articles", articles);
        return "news";
    }

    @RequestMapping("/article")
    public String article(@RequestParam("id") int id, Model model) {
        Article article = newsService.getArticleById(id);
        model.addAttribute("article", article);
        return "article";
    }

}
