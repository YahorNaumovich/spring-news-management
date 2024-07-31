package edu.web.training.service.impl;

import edu.web.training.dao.NewsDao;
import edu.web.training.entity.Article;
import edu.web.training.entity.Category;
import edu.web.training.exception.DaoException;
import edu.web.training.exception.ServiceException;
import edu.web.training.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    private NewsDao newsDao;

    @Override
    public List<Article> getAllArticles() throws ServiceException {

        logger.info("Fetching all articles");

        try {

            List<Article> allArticles = newsDao.getAllArticles();
            logger.debug("Retrieved {} articles", allArticles.size());

            return allArticles;

        } catch (DaoException e) {

            logger.error("Error fetching all articles", e);
            throw new ServiceException("Failed to retrieve all articles", e);

        }
    }

    @Override
    public Article getArticleById(int id) throws ServiceException {

        logger.info("Fetching article with ID: {}", id);

        try {

            Article article = newsDao.getArticleById(id);

            if (article == null) {
                logger.warn("No article found with ID: {}", id);
            }

            logger.debug("Retrieved article with ID: {}", id);

            return article;

        } catch (DaoException e) {

            logger.error("Error fetching article with ID: {}", id, e);
            throw new ServiceException("Failed to retrieve article by ID: " + id, e);

        }
    }

    @Override
    public List<Category> getAllCategories() throws ServiceException {

        logger.info("Fetching all categories");

        try {

            List<Category> categories = newsDao.getAllCategories();
            logger.debug("Retrieved {} categories", categories.size());

            return categories;

        } catch (DaoException e) {

            logger.error("Error fetching all categories", e);
            throw new ServiceException("Failed to retrieve all categories", e);

        }
    }

    @Override
    public List<Article> getArticlesByCategory(int categoryId) throws ServiceException {

        logger.info("Fetching articles for category ID: {}", categoryId);

        try {

            List<Article> articles = newsDao.getArticlesByCategory(categoryId);
            logger.debug("Retrieved {} articles for category ID: {}", articles.size(), categoryId);

            return articles;

        } catch (DaoException e) {

            logger.error("Error fetching articles for category ID: {}", categoryId, e);
            throw new ServiceException("Failed to retrieve articles by category ID: " + categoryId, e);

        }
    }

    @Override
    public void saveArticle(String title, String articleText, String imagePath, int categoryId, int userId) throws ServiceException {

        logger.info("Saving article: {}", title);

        try {

            newsDao.saveArticle(title, articleText, imagePath, categoryId, userId);
            logger.debug("Article saved successfully: {}", title);

        } catch (DaoException e) {

            logger.error("Error saving article: {}", title, e);
            throw new ServiceException("Failed to save article", e);

        }
    }

    @Override
    public void updateArticle(int articleId, String title, String articleText, String imagePath, int categoryId, int userId) throws ServiceException {

        logger.info("Updating article with ID: {}", articleId);

        try {

            newsDao.updateArticle(articleId, title, articleText, imagePath, categoryId, userId);
            logger.debug("Article updated successfully with ID: {}", articleId);

        } catch (DaoException e) {

            logger.error("Error updating article with ID: {}", articleId, e);
            throw new ServiceException("Failed to update article with ID: " + articleId, e);

        }
    }


    @Override
    public void updateArticleWithoutImage(int articleId, String title, String articleText, int categoryId, int userId) throws ServiceException {

        logger.info("Updating article without image with ID: {}", articleId);

        try {

            newsDao.updateArticleWithoutImage(articleId, title, articleText, categoryId, userId);
            logger.debug("Article updated successfully without image with ID: {}", articleId);

        } catch (DaoException e) {

            logger.error("Error updating article without image with ID: {}", articleId, e);
            throw new ServiceException("Failed to update article without image with ID: " + articleId, e);

        }
    }

    @Override
    public void deleteArticle(int id) throws ServiceException {

        logger.info("Deleting article with ID: {}", id);

        try {

            newsDao.deleteArticle(id);
            logger.debug("Article deleted successfully with ID: {}", id);

        } catch (DaoException e) {

            logger.error("Error deleting article with ID: {}", id, e);
            throw new ServiceException("Failed to delete article with ID: " + id, e);

        }
    }
}
