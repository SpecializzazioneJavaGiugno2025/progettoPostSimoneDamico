package it.simo.aulab_post.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.simo.aulab_post.dtos.ArticleDto;
import it.simo.aulab_post.dtos.CategoryDto;
import it.simo.aulab_post.models.Article;
import it.simo.aulab_post.models.Category;
import it.simo.aulab_post.services.CrudService;
import it.simo.aulab_post.services.ArticleService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    @Qualifier("categoryService")
    private CrudService<CategoryDto, Category, Long> categoryService;

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public String articlesIndex(Model viewmModel) {
        viewmModel.addAttribute("title","Tutti gli articoli");
        List<ArticleDto> articles = articleService.readAll();
        Collections.sort(articles,Comparator.comparing(ArticleDto::getPublish_date).reversed());
        viewmModel.addAttribute("articles",articles);
        return "article/articles";
    }

    @GetMapping("create")
    public String articleCreate(Model viewModel) {
        viewModel.addAttribute("title","crea un articolo");
        viewModel.addAttribute("article", new Article());
        viewModel.addAttribute("category",categoryService.readAll());
        return "articles/create";
    }

    @PostMapping
    public String articleStore(@Valid @ModelAttribute("article") Article article,
            BindingResult result, 
            RedirectAttributes redirectAttributes,
            Principal principal,
            MultipartFile file,
            Model viewModel) {
        if(result.hasErrors()) {
            viewModel.addAttribute("title","crea un articolo");
            viewModel.addAttribute("article", article);
            viewModel.addAttribute("category",categoryService.readAll());
            return "articles/create";
        }
        articleService.create(article, principal, file);
        redirectAttributes.addFlashAttribute("message", "Articolo creato con successo");
        return "redirect:/";
    }
}
