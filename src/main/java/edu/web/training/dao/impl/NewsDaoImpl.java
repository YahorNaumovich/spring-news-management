package edu.web.training.dao.impl;

import edu.web.training.dao.NewsDao;
import edu.web.training.entity.Article;
import edu.web.training.entity.Category;
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
}
