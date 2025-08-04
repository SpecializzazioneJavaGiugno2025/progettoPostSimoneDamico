package it.simo.aulab_post.services;

import java.security.Principal;

import it.simo.aulab_post.models.Comment;

public interface  CommentService {
    void addComment(Long id,String body,Principal principal);
}
