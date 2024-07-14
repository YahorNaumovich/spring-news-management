package edu.web.training.controller;

import edu.web.training.entity.Article;
import edu.web.training.entity.Category;
import edu.web.training.service.NewsService;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/")
    public String redirectToMainPage() {
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

    @RequestMapping("/article/add")
    public String goToArticleForm(Model model) {
        List<Category> categories = newsService.getAllCategories();
        model.addAttribute("categories", categories);
        return "article-form";
    }

    @RequestMapping("/article/edit")
    public String goToArticleEditForm(@RequestParam("id") int id, Model model) {
        List<Category> categories = newsService.getAllCategories();
        Article article = newsService.getArticleById(id);
        model.addAttribute("article", article);
        model.addAttribute("categories", categories);
        return "article-form";
    }

    @PostMapping("/article/save")
    public String saveArticle(@RequestParam("title") String title,
                              @RequestParam("articleText") String articleText,
                              @RequestParam("image") MultipartFile image,
                              @RequestParam("category") int categoryId,
                              @RequestParam("userId") int userId) {
        newsService.saveArticle(title, articleText, image, categoryId, userId);
        return "redirect:/news";
    }

    @PostMapping("/article/update")
    public String updateArticle(
            @RequestParam("articleId") int articleId,
            @RequestParam("title") String title,
            @RequestParam("articleText") String articleText,
            @RequestParam("image") MultipartFile image,
            @RequestParam("category") int categoryId,
            @RequestParam("userId") int userId) {
        newsService.updateArticle(articleId,title, articleText, image, categoryId, userId);
        return "redirect:/news";
    }


}
