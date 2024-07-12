package edu.web.training.dao;

import edu.web.training.entity.Article;
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
}
