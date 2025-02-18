package edu.web.training.controller;

import edu.web.training.entity.Article;
import edu.web.training.entity.User;
import edu.web.training.entity.form.ArticleForm;
import edu.web.training.entity.Category;
import edu.web.training.exception.ServiceException;
import edu.web.training.service.NewsService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Controller
public class NewsController {

    private static final String REDIRECT_HOME = "redirect:/";
    private static final String REDIRECT_NEWS = "redirect:/news";
    private static final String NEWS_PAGE = "news";
    private static final String ARTICLE_PAGE = "article";
    private static final String USER_ATTRIBUTE = "user";
    private static final String ARTICLE_FORM_PAGE = "article-form";
    private static final String ARTICLES_ATTRIBUTE = "articles";
    private static final String ARTICLE_ATTRIBUTE = "article";
    private static final String CATEGORIES_ATTRIBUTE = "categories";
    private static final String ARTICLE_FORM_ATTRIBUTE = "articleForm";
    private static final String IS_EDIT_MODE_ATTRIBUTE = "isEditMode";
    private static final String IMAGES_DIRECTORY = "/images";
    private static final String ERROR_ATTRIBUTE = "error";

    @Autowired
    private NewsService newsService;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/")
    public String redirectToMainPage() {
        return REDIRECT_NEWS;
    }

    @RequestMapping("/news")
    public String showAllNews(@RequestParam(value = "category", required = false) Integer categoryId, Model model, Locale locale) {

        try {

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

        } catch (ServiceException e) {

            model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.news.load", null, locale));
            return NEWS_PAGE;

        }
    }

    @RequestMapping("/article")
    public String showArticle(@RequestParam("id") int id, Model model, Locale locale) {

        try {

            Article article = newsService.getArticleById(id);
            model.addAttribute(ARTICLE_ATTRIBUTE, article);

            return ARTICLE_PAGE;

        } catch (ServiceException e) {

            model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.article.load", null, locale));
            return NEWS_PAGE;

        }
    }

    @RequestMapping("/article/add")
    public String goToArticleForm(Model model, Locale locale, HttpSession session) {

        if (!isUserAdminOrEditor(session)) {
            return REDIRECT_HOME;
        }

        try {

            List<Category> categories = newsService.getAllCategories();
            model.addAttribute(ARTICLE_FORM_ATTRIBUTE, new ArticleForm());
            model.addAttribute(CATEGORIES_ATTRIBUTE, categories);
            model.addAttribute(IS_EDIT_MODE_ATTRIBUTE, false);

            return ARTICLE_FORM_PAGE;

        } catch (ServiceException e) {

            model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.article-form.load", null, locale));
            return NEWS_PAGE;

        }
    }

    @RequestMapping("/article/edit")
    public String goToArticleEditForm(@RequestParam("id") int id, Model model, Locale locale, HttpSession session) {

        if (!isUserAdminOrEditor(session)) {
            return REDIRECT_HOME;
        }

        try {

            List<Category> categories = newsService.getAllCategories();
            Article article = newsService.getArticleById(id);

            ArticleForm articleForm = new ArticleForm(article.getId(), article.getTitle(), article.getArticleText().getText(), null, article.getCategory().getId(), article.getUser().getId());

            model.addAttribute(ARTICLE_FORM_ATTRIBUTE, articleForm);
            model.addAttribute(CATEGORIES_ATTRIBUTE, categories);
            model.addAttribute(IS_EDIT_MODE_ATTRIBUTE, true);

            return ARTICLE_FORM_PAGE;

        } catch (ServiceException e) {

            model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.edit-article-form.load", null, locale));
            return NEWS_PAGE;

        }
    }

    @PostMapping("/article/save")
    public String saveArticle(@Valid @ModelAttribute ArticleForm articleForm, BindingResult bindingResult, Model model, Locale locale, HttpSession session) {

        if (!isUserAdminOrEditor(session)) {
            return REDIRECT_HOME;
        }

        if (bindingResult.hasErrors()) {

            try {
                List<Category> categories = newsService.getAllCategories();
                model.addAttribute(CATEGORIES_ATTRIBUTE, categories);
                model.addAttribute(IS_EDIT_MODE_ATTRIBUTE, false);

                return ARTICLE_FORM_PAGE;

            } catch (ServiceException e) {

                model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.categories.load", null, locale));
                return ARTICLE_FORM_PAGE;

            }
        }

        try {

            String relativePath = handleImageUpload(articleForm.getImage());

            newsService.saveArticle(articleForm.getTitle(), articleForm.getArticleText(),
                    relativePath, articleForm.getCategoryId(), articleForm.getUserId());

            return REDIRECT_NEWS;

        } catch (ServiceException e) {

            model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.article.save", null, locale));
            return ARTICLE_FORM_PAGE;

        }
    }

    @PostMapping("/article/update")
    public String updateArticle(@Valid @ModelAttribute ArticleForm articleForm, BindingResult bindingResult, Model model, Locale locale, HttpSession session) {

        if (!isUserAdminOrEditor(session)) {
            return REDIRECT_HOME;
        }

        if (bindingResult.hasErrors()) {

            try {
                List<Category> categories = newsService.getAllCategories();
                model.addAttribute(CATEGORIES_ATTRIBUTE, categories);
                model.addAttribute(IS_EDIT_MODE_ATTRIBUTE, true);

                return ARTICLE_FORM_PAGE;

            } catch (ServiceException e) {

                model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.categories.load", null, locale));
                return ARTICLE_FORM_PAGE;

            }
        }

        try {

            if (articleForm.getImage().isEmpty()) {

                newsService.updateArticleWithoutImage(articleForm.getArticleId(), articleForm.getTitle(), articleForm.getArticleText(), articleForm.getCategoryId(), articleForm.getUserId());

            } else {

                String relativePath = handleImageUpload(articleForm.getImage());

                newsService.updateArticle(articleForm.getArticleId(), articleForm.getTitle(), articleForm.getArticleText(),
                        relativePath, articleForm.getCategoryId(), articleForm.getUserId());

            }

            return REDIRECT_NEWS;

        } catch (ServiceException e) {

            model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.article.update", null, locale));
            return ARTICLE_FORM_PAGE;

        }
    }

    @RequestMapping("/article/delete")
    public String deleteArticle(@RequestParam("id") int id, Model model, Locale locale, HttpSession session) {

        if (!isUserAdminOrEditor(session)) {
            return REDIRECT_HOME;
        }

        try {

            newsService.deleteArticle(id);
            return REDIRECT_NEWS;

        } catch (ServiceException e) {

            model.addAttribute(ERROR_ATTRIBUTE, messageSource.getMessage("error.article.delete", null, locale));
            return NEWS_PAGE;

        }
    }

    private String handleImageUpload(MultipartFile image) {

        if (image.isEmpty()) {
            return null;
        }

        String uploadPath = servletContext.getRealPath(IMAGES_DIRECTORY);
        String filePath = uploadPath + File.separator + image.getOriginalFilename();

        File dest = new File(filePath);

        try {

            image.transferTo(dest);

        } catch (IOException e) {

            throw new RuntimeException("Failed to upload image", e);

        }

        return IMAGES_DIRECTORY + "/" + image.getOriginalFilename();
    }

    private boolean isUserAdminOrEditor(HttpSession session) {

        User user = (User) session.getAttribute(USER_ATTRIBUTE);
        return user != null && (user.getUserRole().getName().equals("Admin") || user.getUserRole().getName().equals("Editor"));

    }

}
