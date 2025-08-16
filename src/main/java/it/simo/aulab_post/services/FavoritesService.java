package it.simo.aulab_post.services;

import it.simo.aulab_post.models.User;

public interface FavoritesService {
    void  addFavorite(Long id, User user);

}
