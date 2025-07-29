package it.simo.aulab_post.dtos;

import it.simo.aulab_post.models.Category;
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
    private String publish_date;
    private User user;
    private Category category;
}
