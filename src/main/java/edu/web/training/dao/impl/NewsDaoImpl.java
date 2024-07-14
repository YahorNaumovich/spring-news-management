package edu.web.training.dao.impl;

import edu.web.training.dao.NewsDao;
import edu.web.training.entity.Article;
import edu.web.training.entity.ArticleText;
import edu.web.training.entity.Category;
import edu.web.training.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDaoImpl implements NewsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Article> getAllArticles() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Article", Article.class)
                .getResultList();
    }

    @Override
    public Article getArticleById(int id) {
        return sessionFactory
                .getCurrentSession()
                .get(Article.class, id);
    }

    @Override
    public List<Category> getAllCategories() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Category", Category.class)
                .getResultList();
    }

    @Override
    public List<Article> getArticlesByCategory(int categoryId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Article where category.id = :categoryId", Article.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public void saveArticle(String title, String articleText, String filePath, int categoryId, int userId) {


        // Create and save ArticleText entity
        ArticleText text = new ArticleText();
        text.setText(articleText);
        sessionFactory.getCurrentSession().persist(text);

        // Create Article entity and set properties
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

        // Save Article entity
        sessionFactory
                .getCurrentSession()
                .persist(article);
    }

    @Override
    public void updateArticleWithoutImage(int articleId, String title, String articleText, int categoryId, int userId) {
        Article article = getArticleById(articleId);
        if (article != null) {
            article.setTitle(title);
            article.getArticleText().setText(articleText);
            article.setCategory(sessionFactory.getCurrentSession().get(Category.class, categoryId));
            sessionFactory.getCurrentSession().merge(article);
        }
    }

    @Override
    public void updateArticle(int articleId, String title, String articleText, String relativePath, int categoryId, int userId) {
        Article article = getArticleById(articleId);
        if (article != null) {
            article.setTitle(title);
            article.getArticleText().setText(articleText);
            article.setImagePath(relativePath);
            article.setCategory(sessionFactory.getCurrentSession().get(Category.class, categoryId));
            sessionFactory.getCurrentSession().merge(article);
        }
    }
}
