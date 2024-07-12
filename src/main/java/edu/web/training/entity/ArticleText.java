package edu.web.training.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "ArticleText")
public class ArticleText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String text;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}