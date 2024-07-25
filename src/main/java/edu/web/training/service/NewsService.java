package edu.web.training.service;

import edu.web.training.entity.Article;
import edu.web.training.entity.Category;
import edu.web.training.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    List<Article> getAllArticles() throws ServiceException;

    Article getArticleById(int id) throws ServiceException;

    List<Category> getAllCategories() throws ServiceException;

    List<Article> getArticlesByCategory(int categoryId) throws ServiceException;

    void saveArticle(String title, String articleText, String imagePath, int categoryId, int userId) throws ServiceException;

    void updateArticle(int articleId, String title, String articleText, String imagePath, int categoryId, int userId) throws ServiceException;

    void updateArticleWithoutImage(int articleId, String title, String articleText, int categoryId, int userId) throws ServiceException;

    void deleteArticle(int id) throws ServiceException;
}
