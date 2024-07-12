package edu.web.training.dao;

import edu.web.training.entity.Article;
import edu.web.training.entity.Category;

import java.util.List;

public interface NewsDao {

    List<Article> getAllArticles();

    Article getArticleById(int id);

    List<Category> getAllCategories();
}
