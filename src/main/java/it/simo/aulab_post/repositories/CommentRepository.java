package it.simo.aulab_post.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.simo.aulab_post.models.Article;
import it.simo.aulab_post.models.Comment;
import it.simo.aulab_post.models.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUser(User user);
    List<Comment> findByArticle(Article article);
    
}
