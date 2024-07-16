package edu.web.training.service;

import edu.web.training.entity.Article;
import edu.web.training.entity.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    List<Article> getAllArticles();

    Article getArticleById(int id);

    List<Category> getAllCategories();

    List<Article> getArticlesByCategory(int categoryId);

    void saveArticle(String title, String articleText, MultipartFile image, int categoryId, int userId);

    void updateArticle(int articleId ,String title, String articleText, MultipartFile image, int categoryId, int userId);

    void deleteArticle(int id);
}
