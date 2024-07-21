package edu.web.training.entity.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ArticleForm {

    private int articleId; // For update only

    @NotBlank(message = "{article-form.title.required}")
    @Size(max = 100, message = "{article-form.title.size}")
    private String title;

    @NotBlank(message = "{article-form.articleText.required}")
    @Size(max = 16777215, message = "{article-form.articleText.size}")
    private String articleText;

    private MultipartFile image;

    private int categoryId;

    private int userId;

}
