package edu.web.training.service.impl;

import edu.web.training.dao.NewsDao;
import edu.web.training.entity.Article;
import edu.web.training.entity.Category;
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
    public List<Article> getAllArticles() {
        return newsDao.getAllArticles();
    }

    @Override
    public Article getArticleById(int id) {
        return newsDao.getArticleById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return newsDao.getAllCategories();
    }

    @Override
    public List<Article> getArticlesByCategory(int categoryId) {
        return newsDao.getArticlesByCategory(categoryId);
    }
}
