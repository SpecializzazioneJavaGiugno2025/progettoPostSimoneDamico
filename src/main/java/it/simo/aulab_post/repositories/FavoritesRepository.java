package it.simo.aulab_post.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.simo.aulab_post.models.Favorites;

public interface FavoritesRepository extends CrudRepository<Favorites, Long> {
    @Query(value="SELECT article_id FROM articles_users WHERE user_id = :id",nativeQuery=true)
    List<Long> findByUserId(@Param("id") Long id);

    @Query(value="SELECT user_id FROM articles_users WHERE article_id = :id",nativeQuery=true)
    List<Long> findByArticleId(@Param("id") Long id);

    @Query(value="SELECT * FROM articles_users WHERE user_id = :id AND article_id = :articleId",nativeQuery=true)
    Favorites findByUserIdAndArticleId(@Param("id") Long id, @Param("articleId") Long articleId);
}
