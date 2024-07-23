package edu.web.training.service.impl;

import edu.web.training.dao.NewsDao;
import edu.web.training.entity.Article;
import edu.web.training.entity.Category;
import edu.web.training.service.NewsService;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @Override
    public void saveArticle(String title, String articleText, String imagePath, int categoryId, int userId) {

        newsDao.saveArticle(title, articleText, imagePath, categoryId, userId);

    }

    @Override
    public void updateArticle(int articleId, String title, String articleText, String imagePath, int categoryId, int userId) {

        newsDao.updateArticle(articleId, title, articleText, imagePath, categoryId, userId);

    }


    @Override
    public void updateArticleWithoutImage(int articleId, String title, String articleText, int categoryId, int userId) {

        newsDao.updateArticleWithoutImage(articleId, title, articleText, categoryId, userId);

    }

    @Override
    public void deleteArticle(int id) {
        newsDao.deleteArticle(id);
    }
}
