package it.simo.aulab_post.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.simo.aulab_post.dtos.ArticleDto;
import it.simo.aulab_post.dtos.CategoryDto;
import it.simo.aulab_post.models.Category;
import it.simo.aulab_post.services.ArticleService;
import it.simo.aulab_post.services.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/search/{id}")
    public String categorySearch(@PathVariable("id") Long id, Model viewModel) {
        CategoryDto category= categoryService.read(id);
        viewModel.addAttribute("title","Tutti gli articoli trovati per categoria: "+category.getName());
        List<ArticleDto> articles = articleService.searchByCategory(modelMapper.map(category, Category.class));
        viewModel.addAttribute("articles",articles);
        return "article/articles";
    }
}
