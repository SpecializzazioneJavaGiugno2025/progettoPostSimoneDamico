package it.simo.aulab_post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.simo.aulab_post.dtos.CategoryDto;
import it.simo.aulab_post.models.Article;
import it.simo.aulab_post.models.Category;
import it.simo.aulab_post.services.CrudService;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    @Qualifier("categoryService")
    private CrudService<CategoryDto, Category, Long> categoryService;

    @GetMapping("create")
    public String articleCreate(Model viewModel) {
        viewModel.addAttribute("title","crea un articolo");
        viewModel.addAttribute("article", new Article());
        viewModel.addAttribute("category",categoryService.readAll());
        return "articles/create";
    }
}
