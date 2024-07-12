package edu.web.training.controller;

import edu.web.training.entity.Article;
import edu.web.training.entity.Category;
import edu.web.training.service.NewsService;
import edu.web.training.service.NewsServiceImpl;
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
    public String showAllNews(@RequestParam(value = "category", required = false) Integer categoryId, Model model) {
        List<Article> articles;
        if (categoryId != null) {
            articles = newsService.getArticlesByCategory(categoryId);
        } else {
            articles = newsService.getAllArticles();
        }
        List<Category> categories = newsService.getAllCategories();
        model.addAttribute("articles", articles);
        model.addAttribute("categories", categories);
        return "news";
    }

    @RequestMapping("/article")
    public String showArticle(@RequestParam("id") int id, Model model) {
        Article article = newsService.getArticleById(id);
        model.addAttribute("article", article);
        return "article";
    }

}
