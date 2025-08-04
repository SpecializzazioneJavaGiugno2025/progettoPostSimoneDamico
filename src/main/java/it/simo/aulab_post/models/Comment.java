package it.simo.aulab_post.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message="il commento non può essere vuoto")
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({ "comments" })
    private User user;

    @ManyToOne
    @JoinColumn(name="article_id")
    @JsonIgnoreProperties({"comments"})
    private Article article;

}
