package edu.web.training.dao.impl;

import edu.web.training.dao.NewsDao;
import edu.web.training.entity.Article;
import edu.web.training.entity.ArticleText;
import edu.web.training.entity.Category;
import edu.web.training.entity.User;
import edu.web.training.exception.DaoException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDaoImpl implements NewsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Article> getAllArticles() throws DaoException {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .createQuery("from Article", Article.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException("Failed to retrieve all articles", e);
        }
    }

    @Override
    public Article getArticleById(int id) throws DaoException {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .get(Article.class, id);
        } catch (Exception e) {
            throw new DaoException("Failed to retrieve article with ID: " + id, e);
        }
    }

    @Override
    public List<Category> getAllCategories() throws DaoException {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .createQuery("from Category", Category.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException("Failed to retrieve all categories", e);
        }
    }

    @Override
    public List<Article> getArticlesByCategory(int categoryId) throws DaoException {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .createQuery("from Article where category.id = :categoryId", Article.class)
                    .setParameter("categoryId", categoryId)
                    .getResultList();
        } catch (Exception e) {
            throw new DaoException("Failed to retrieve articles by category ID: " + categoryId, e);
        }
    }

    @Override
    public void saveArticle(String title, String articleText, String filePath, int categoryId, int userId) throws DaoException {
        try {
            ArticleText text = new ArticleText();
            text.setText(articleText);
            sessionFactory.getCurrentSession().persist(text);

            Article article = new Article();
            article.setTitle(title);
            article.setArticleText(text);
            article.setImagePath(filePath);

            article.setCategory(sessionFactory
                    .getCurrentSession()
                    .get(Category.class, categoryId));

            article.setUser(sessionFactory
                    .getCurrentSession()
                    .get(User.class, userId));

            sessionFactory.getCurrentSession().persist(article);
        } catch (Exception e) {
            throw new DaoException("Failed to save article", e);
        }
    }

    @Override
    public void updateArticleWithoutImage(int articleId, String title, String articleText, int categoryId, int userId) throws DaoException {
        try {
            Article article = getArticleById(articleId);

            if (article != null) {
                article.setTitle(title);
                article.getArticleText().setText(articleText);
                article.setCategory(sessionFactory.getCurrentSession().get(Category.class, categoryId));
                sessionFactory.getCurrentSession().merge(article);
            }
        } catch (Exception e) {
            throw new DaoException("Failed to update article without image with ID: " + articleId, e);
        }
    }

    @Override
    public void updateArticle(int articleId, String title, String articleText, String relativePath, int categoryId, int userId) throws DaoException {
        try {
            Article article = getArticleById(articleId);

            if (article != null) {
                article.setTitle(title);
                article.getArticleText().setText(articleText);
                article.setImagePath(relativePath);
                article.setCategory(sessionFactory.getCurrentSession().get(Category.class, categoryId));
                sessionFactory.getCurrentSession().merge(article);
            }
        } catch (Exception e) {
            throw new DaoException("Failed to update article with image with ID: " + articleId, e);
        }
    }

    @Override
    public void deleteArticle(int id) throws DaoException {
        try {
            Article article = getArticleById(id);

            if (article != null) {
                sessionFactory.getCurrentSession().remove(article);
            }
        } catch (Exception e) {
            throw new DaoException("Failed to delete article with ID: " + id, e);
        }
    }
}
