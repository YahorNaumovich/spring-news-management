package edu.web.training.service;

import edu.web.training.entity.Article;

import java.util.List;

public interface NewsService {
    List<Article> getAllArticles();

    Article getArticleById(int id);
}
