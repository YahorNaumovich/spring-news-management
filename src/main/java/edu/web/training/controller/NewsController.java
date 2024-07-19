package edu.web.training.controller;

import edu.web.training.entity.Article;
import edu.web.training.entity.form.ArticleForm;
import edu.web.training.entity.Category;
import edu.web.training.service.NewsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("articleForm", new ArticleForm());
        model.addAttribute("categories", categories);
        model.addAttribute("isEditMode", false);
        return "article-form";
    }

    @RequestMapping("/article/edit")
    public String goToArticleEditForm(@RequestParam("id") int id, Model model) {
        List<Category> categories = newsService.getAllCategories();
        Article article = newsService.getArticleById(id);
        ArticleForm articleForm = new ArticleForm();
        articleForm.setArticleId(article.getId());
        articleForm.setTitle(article.getTitle());
        articleForm.setArticleText(article.getArticleText().getText());
        articleForm.setCategoryId(article.getCategory().getId());
        articleForm.setUserId(article.getUser().getId());
        model.addAttribute("articleForm", articleForm);
        model.addAttribute("categories", categories);
        model.addAttribute("isEditMode", true);
        return "article-form";
    }

    @PostMapping("/article/save")
    public String saveArticle(@Valid @ModelAttribute ArticleForm articleForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Category> categories = newsService.getAllCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("isEditMode", false);
            return "article-form";
        }
        newsService.saveArticle(articleForm.getTitle(), articleForm.getArticleText(),
                articleForm.getImage(), articleForm.getCategoryId(), articleForm.getUserId());
        return "redirect:/news";
    }

    @PostMapping("/article/update")
    public String updateArticle(@Valid @ModelAttribute ArticleForm articleForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Category> categories = newsService.getAllCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("isEditMode", true);
            return "article-form";
        }
        newsService.updateArticle(articleForm.getArticleId(), articleForm.getTitle(), articleForm.getArticleText(),
                articleForm.getImage(), articleForm.getCategoryId(), articleForm.getUserId());
        return "redirect:/news";
    }

    @RequestMapping("/article/delete")
    public String deleteArticle(@RequestParam("id") int id) {
        newsService.deleteArticle(id);
        return "redirect:/news";
    }


}
