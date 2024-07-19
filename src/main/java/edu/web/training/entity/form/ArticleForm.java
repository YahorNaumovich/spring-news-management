package edu.web.training.entity.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ArticleForm {

    private int articleId; // For update only

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be up to 100 characters")
    private String title;

    @NotBlank(message = "Article text is required")
    @Size(max = 16777215, message = "Article text must be up to 16777215 characters")
    private String articleText;

    private MultipartFile image;

    private int categoryId;

    private int userId;

}
