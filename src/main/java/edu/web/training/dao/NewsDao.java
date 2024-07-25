package edu.web.training.dao;

import edu.web.training.entity.Article;
import edu.web.training.entity.Category;
import edu.web.training.exception.DaoException;

import java.util.List;

public interface NewsDao {

    List<Article> getAllArticles() throws DaoException;

    Article getArticleById(int id) throws DaoException;

    List<Category> getAllCategories() throws DaoException;

    List<Article> getArticlesByCategory(int categoryId) throws DaoException;

    void saveArticle(String title, String articleText, String filePath, int categoryId, int userId) throws DaoException;

    void updateArticleWithoutImage(int articleId, String title, String articleText, int categoryId, int userId) throws DaoException;

    void updateArticle(int articleId, String title, String articleText, String relativePath, int categoryId, int userId) throws DaoException;

    void deleteArticle(int id) throws DaoException;
}
