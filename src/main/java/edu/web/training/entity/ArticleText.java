package edu.web.training.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ArticleText")
@Data
public class ArticleText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String text;

}