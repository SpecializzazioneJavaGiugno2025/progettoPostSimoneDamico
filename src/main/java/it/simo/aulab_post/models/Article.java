package it.simo.aulab_post.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String title;

    @Column(nullable = false, length = 100)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String subtitle;

    @Column(nullable = false, length = 1000)
    @NotEmpty
    @Size(max = 1000)
    private String body;

    @Column(nullable = true, length = 8)
    @NotNull
    private LocalDate publishDate;

    @Column(nullable = true)
    private Boolean isAccepted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({ "articles" })
    private User user;

    @ManyToOne
    @JsonIgnoreProperties({ "articles" })
    private Category category;

    @OneToOne(mappedBy = "article")
    @JsonIgnoreProperties({ "article" })
    private Image image;

    @Override
    public boolean equals(Object obj) {
        Article article = (Article) obj;
        if (title.equals(article.getTitle())
                && subtitle.equals(article.getSubtitle())
                && body.equals(article.getBody())
                && publishDate.equals(article.getPublishDate())
                && category.getName().equals(article.getCategory().getName())
                && image.getPath().equals(article.getImage().getPath())) {
            return true;
        }
        return false;
    }
}
