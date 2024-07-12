package edu.web.training.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "imagePath")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "User_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ArticleText_id", nullable = false)
    private ArticleText articleText;

    @ManyToOne
    @JoinColumn(name = "Category_id", nullable = false)
    private Category category;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArticleText getArticleText() {
        return articleText;
    }

    public void setArticleText(ArticleText articleText) {
        this.articleText = articleText;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}