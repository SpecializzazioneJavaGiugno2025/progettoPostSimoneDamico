package it.simo.aulab_post.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.simo.aulab_post.models.Comment;
import it.simo.aulab_post.repositories.ArticleRepository;
import it.simo.aulab_post.repositories.CommentRepository;
import it.simo.aulab_post.services.ArticleService;
import it.simo.aulab_post.services.CommentService;

@Controller
public class CommentController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CommentRepository commentRepository;

    @PostMapping("comment/create/{id}")
    public String commentCreate(Model viewModel, @ModelAttribute("comment") Comment comment, BindingResult result,
            RedirectAttributes redirectAttributes, Principal principal, @PathVariable("id") Long id) {
        if (result.hasErrors()) {
            viewModel.addAttribute("title", "Dettagli articolo");
            viewModel.addAttribute("article", articleService.read(id));
            viewModel.addAttribute("comments", commentRepository.findByArticle(articleRepository.findById(id).get()));
            return "redirect:/articles/detail/" + id;
        }
        String body = comment.getBody();

        commentService.addComment(id, body, principal);

        return "redirect:/articles/detail/" + id;
    }
}
