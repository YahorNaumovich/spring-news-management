package edu.web.training.service.impl;

import edu.web.training.dao.NewsDao;
import edu.web.training.entity.Article;
import edu.web.training.entity.Category;
import edu.web.training.exception.DaoException;
import edu.web.training.exception.ServiceException;
import edu.web.training.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public List<Article> getAllArticles() throws ServiceException {
        try {
            return newsDao.getAllArticles();
        } catch (DaoException e) {
            throw new ServiceException("Failed to retrieve all articles", e);
        }
    }

    @Override
    public Article getArticleById(int id) throws ServiceException {
        try {
            return newsDao.getArticleById(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to retrieve article by ID: " + id, e);
        }
    }

    @Override
    public List<Category> getAllCategories() throws ServiceException {
        try {
            return newsDao.getAllCategories();
        } catch (DaoException e) {
            throw new ServiceException("Failed to retrieve all categories", e);
        }
    }

    @Override
    public List<Article> getArticlesByCategory(int categoryId) throws ServiceException {
        try {
            return newsDao.getArticlesByCategory(categoryId);
        } catch (DaoException e) {
            throw new ServiceException("Failed to retrieve articles by category ID: " + categoryId, e);
        }
    }

    @Override
    public void saveArticle(String title, String articleText, String imagePath, int categoryId, int userId) throws ServiceException {
        try {
            newsDao.saveArticle(title, articleText, imagePath, categoryId, userId);
        } catch (DaoException e) {
            throw new ServiceException("Failed to save article", e);
        }
    }

    @Override
    public void updateArticle(int articleId, String title, String articleText, String imagePath, int categoryId, int userId) throws ServiceException {
        try {
            newsDao.updateArticle(articleId, title, articleText, imagePath, categoryId, userId);
        } catch (DaoException e) {
            throw new ServiceException("Failed to update article with ID: " + articleId, e);
        }
    }


    @Override
    public void updateArticleWithoutImage(int articleId, String title, String articleText, int categoryId, int userId) throws ServiceException {
        try {
            newsDao.updateArticleWithoutImage(articleId, title, articleText, categoryId, userId);
        } catch (DaoException e) {
            throw new ServiceException("Failed to update article without image with ID: " + articleId, e);
        }
    }

    @Override
    public void deleteArticle(int id) throws ServiceException {
        try {
            newsDao.deleteArticle(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete article with ID: " + id, e);
        }
    }
}
