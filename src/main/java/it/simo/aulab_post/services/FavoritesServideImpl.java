package it.simo.aulab_post.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.simo.aulab_post.models.Article;
import it.simo.aulab_post.models.Favorites;
import it.simo.aulab_post.models.User;
import it.simo.aulab_post.repositories.ArticleRepository;
import it.simo.aulab_post.repositories.FavoritesRepository;
@Service
public class FavoritesServideImpl implements FavoritesService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Override
    public void addFavorite(Long id, User user) {
        Article article = articleRepository.findById(id).get();
        if(favoritesRepository.findByUserIdAndArticleId( user.getId(), id)!=null ){ {
            favoritesRepository.delete(favoritesRepository.findByUserIdAndArticleId( user.getId(), id));
        }
        } else {
            
            Favorites favorite = new Favorites();
            favorite.setArticle(article);
            favorite.setUser(user);
            favoritesRepository.save(favorite);
        }
        
    }

}
