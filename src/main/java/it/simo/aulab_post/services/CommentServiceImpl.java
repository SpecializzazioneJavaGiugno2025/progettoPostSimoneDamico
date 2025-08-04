package it.simo.aulab_post.services;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import it.simo.aulab_post.models.Article;
import it.simo.aulab_post.models.Comment;
import it.simo.aulab_post.models.User;
import it.simo.aulab_post.repositories.ArticleRepository;
import it.simo.aulab_post.repositories.CommentRepository;
import it.simo.aulab_post.repositories.UserRepository;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void addComment(Long id, Comment comment, Principal principal) {
        Article article=articleRepository.findById(id).get();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = (userRepository.findById(userDetails.getId())).get();
            comment.setUser(user);
        }

        comment.setArticle(article);
        commentRepository.save(comment);
        
    }

}
