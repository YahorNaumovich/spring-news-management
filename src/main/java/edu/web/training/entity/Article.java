package edu.web.training.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Article")
@Data
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

}