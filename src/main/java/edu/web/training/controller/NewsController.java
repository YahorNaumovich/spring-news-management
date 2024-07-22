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

    private static final String REDIRECT_NEWS = "redirect:/news";
    private static final String NEWS_PAGE = "news";
    private static final String ARTICLE_PAGE = "article";
    private static final String ARTICLE_FORM_PAGE = "article-form";
    private static final String ARTICLES_ATTRIBUTE = "articles";
    private static final String ARTICLE_ATTRIBUTE = "article";
    private static final String CATEGORIES_ATTRIBUTE = "categories";
    private static final String ARTICLE_FORM_ATTRIBUTE = "articleForm";
    private static final String IS_EDIT_MODE_ATTRIBUTE = "isEditMode";

    @Autowired
    private NewsService newsService;

    @RequestMapping("/")
    public String redirectToMainPage() {
        return REDIRECT_NEWS;
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
        model.addAttribute(ARTICLES_ATTRIBUTE, articles);
        model.addAttribute(CATEGORIES_ATTRIBUTE, categories);

        return NEWS_PAGE;

    }

    @RequestMapping("/article")
    public String showArticle(@RequestParam("id") int id, Model model) {

        Article article = newsService.getArticleById(id);
        model.addAttribute(ARTICLE_ATTRIBUTE, article);

        return ARTICLE_PAGE;
    }

    @RequestMapping("/article/add")
    public String goToArticleForm(Model model) {

        List<Category> categories = newsService.getAllCategories();

        model.addAttribute(ARTICLE_FORM_ATTRIBUTE, new ArticleForm());
        model.addAttribute(CATEGORIES_ATTRIBUTE, categories);
        model.addAttribute(IS_EDIT_MODE_ATTRIBUTE, false);

        return ARTICLE_FORM_PAGE;
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

        model.addAttribute(ARTICLE_FORM_ATTRIBUTE, articleForm);
        model.addAttribute(CATEGORIES_ATTRIBUTE, categories);
        model.addAttribute(IS_EDIT_MODE_ATTRIBUTE, true);

        return ARTICLE_FORM_PAGE;
    }

    @PostMapping("/article/save")
    public String saveArticle(@Valid @ModelAttribute ArticleForm articleForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            List<Category> categories = newsService.getAllCategories();
            model.addAttribute(CATEGORIES_ATTRIBUTE, categories);
            model.addAttribute(IS_EDIT_MODE_ATTRIBUTE, false);

            return ARTICLE_FORM_PAGE;
        }

        newsService.saveArticle(articleForm.getTitle(), articleForm.getArticleText(),
                articleForm.getImage(), articleForm.getCategoryId(), articleForm.getUserId());

        return REDIRECT_NEWS;
    }

    @PostMapping("/article/update")
    public String updateArticle(@Valid @ModelAttribute ArticleForm articleForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            List<Category> categories = newsService.getAllCategories();
            model.addAttribute(CATEGORIES_ATTRIBUTE, categories);
            model.addAttribute(IS_EDIT_MODE_ATTRIBUTE, true);

            return ARTICLE_FORM_PAGE;
        }

        newsService.updateArticle(articleForm.getArticleId(), articleForm.getTitle(), articleForm.getArticleText(),
                articleForm.getImage(), articleForm.getCategoryId(), articleForm.getUserId());

        return REDIRECT_NEWS;
    }

    @RequestMapping("/article/delete")
    public String deleteArticle(@RequestParam("id") int id) {

        newsService.deleteArticle(id);

        return REDIRECT_NEWS;

    }
}
