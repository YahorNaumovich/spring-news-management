package edu.web.training.service;

import edu.web.training.dao.NewsDao;
import edu.web.training.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsService {
    @Autowired
    private NewsDao newsDao;

    public List<Article> getAllArticles() {
        return newsDao.getAllArticles();
    }
}
