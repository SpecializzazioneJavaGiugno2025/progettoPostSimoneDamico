package it.simo.aulab_post.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import it.simo.aulab_post.models.Article;
import it.simo.aulab_post.models.Category;
import it.simo.aulab_post.models.User;

public interface ArticleRepository extends ListCrudRepository<Article, Long>{
    List<Article> findByCategory(Category category);
    List<Article> findByUser(User user);
    List<Article> findByIsAcceptedTrue();
    List<Article> findByIsAcceptedFalse();
    List<Article> findByIsAcceptedIsNull();

}
