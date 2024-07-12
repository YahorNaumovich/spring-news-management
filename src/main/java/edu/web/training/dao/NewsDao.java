package edu.web.training.dao;

import edu.web.training.entity.Article;

import java.util.List;

public interface NewsDao {

    List<Article> getAllArticles();

    Article getArticleById(int id);
}
