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

    @Autowired
    private ServletContext servletContext;

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
    public void saveArticle(String title, String articleText, MultipartFile image, int categoryId, int userId) {

        String uploadPath = servletContext.getRealPath("/images");
        String filePath = uploadPath + File.separator + image.getOriginalFilename();
        File dest = new File(filePath);

        try {
            image.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Save the relative path to the database
        String relativePath = "/images/" + image.getOriginalFilename();

        System.out.println(relativePath);

        newsDao.saveArticle(title, articleText, relativePath, categoryId, userId);
    }

    @Override
    public void updateArticle(int articleId, String title, String articleText, MultipartFile image, int categoryId, int userId) {

        if (image.isEmpty()) {
            newsDao.updateArticleWithoutImage(articleId, title, articleText, categoryId, userId);
        } else {
            String uploadPath = servletContext.getRealPath("/images");
            String filePath = uploadPath + File.separator + image.getOriginalFilename();
            File dest = new File(filePath);

            try {
                image.transferTo(dest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Save the relative path to the database
            String relativePath = "/images/" + image.getOriginalFilename();

            newsDao.updateArticle(articleId, title, articleText, relativePath, categoryId, userId);
        }
    }
}
