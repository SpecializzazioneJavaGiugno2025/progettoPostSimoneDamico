package it.simo.aulab_post.dtos;

import java.time.LocalDate;

import it.simo.aulab_post.models.Category;
import it.simo.aulab_post.models.Image;
import it.simo.aulab_post.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String subtitle;
    private String body;
    private LocalDate publishDate;
    private Boolean isAccepted;
    private User user;
    private Category category;
    private Image image;
}
