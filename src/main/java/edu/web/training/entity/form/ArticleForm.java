package edu.web.training.entity.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ArticleForm {

    private int articleId; // For update only
    private String title;
    private String articleText;
    private MultipartFile image;
    private int categoryId;
    private int userId;

}
