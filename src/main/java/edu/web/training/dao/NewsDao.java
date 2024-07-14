package edu.web.training.dao;

import edu.web.training.entity.Article;
import edu.web.training.entity.Category;

import java.util.List;

public interface NewsDao {

    List<Article> getAllArticles();

    Article getArticleById(int id);

    List<Category> getAllCategories();

    List<Article> getArticlesByCategory(int categoryId);

    void saveArticle(String title, String articleText, String filePath, int categoryId, int userId);

    void updateArticleWithoutImage(int articleId, String title, String articleText, int categoryId, int userId);

    void updateArticle(int articleId, String title, String articleText, String relativePath, int categoryId, int userId);
}
