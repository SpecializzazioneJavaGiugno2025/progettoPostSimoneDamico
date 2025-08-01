package it.simo.aulab_post.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.simo.aulab_post.dtos.ArticleDto;
import it.simo.aulab_post.dtos.CategoryDto;
import it.simo.aulab_post.models.Article;
import it.simo.aulab_post.models.Category;
import it.simo.aulab_post.repositories.ArticleRepository;
import it.simo.aulab_post.services.ArticleService;
import it.simo.aulab_post.services.CrudService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    @Qualifier("categoryService")
    private CrudService<CategoryDto, Category, Long> categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public String articlesIndex(Model viewmModel) {
        viewmModel.addAttribute("title", "Tutti gli articoli");
        List<ArticleDto> articles = new ArrayList<>();
        for (Article article : articleRepository.findByIsAcceptedTrue()) {
            articles.add(modelMapper.map(article, ArticleDto.class));
        }
        Collections.sort(articles, Comparator.comparing(ArticleDto::getPublishDate).reversed());
        viewmModel.addAttribute("articles", articles);
        return "articles/articles";
    }

    @GetMapping("create")
    public String articleCreate(Model viewModel) {
        viewModel.addAttribute("title", "Crea un articolo");
        viewModel.addAttribute("article", new Article());
        viewModel.addAttribute("categories", categoryService.readAll());
        return "articles/create";
    }

    @PostMapping
    public String articleStore(@Valid @ModelAttribute("article") Article article,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Principal principal,
            MultipartFile file,
            Model viewModel) {
        if (result.hasErrors()) {
            viewModel.addAttribute("title", "Crea un articolo");
            viewModel.addAttribute("article", article);
            viewModel.addAttribute("categories", categoryService.readAll());
            return "articles/create";
        }
        articleService.create(article, principal, file);
        redirectAttributes.addFlashAttribute("message", "Articolo creato con successo");
        return "redirect:/";
    }

    @GetMapping("detail/{id}")
    public String articleDetails(@PathVariable("id") Long id, Model viewModel) {
        viewModel.addAttribute("title", "Dettagli articolo");
        viewModel.addAttribute("article", articleService.read(id));
        return "articles/detail";
    }

    @GetMapping("/edit/{id}")
    public String aditArticle(@PathVariable("id") Long id, Model viewModel) {
        viewModel.addAttribute("title", "Aggiorna articolo");
        viewModel.addAttribute("categories", categoryService.readAll());
        viewModel.addAttribute("article", articleService.read(id));
        return "articles/edit";
    }

    @PostMapping("/update/{id}")
    public String articleUpdate(@PathVariable("id") Long id, @Valid @ModelAttribute("article") Article article,
            BindingResult result, RedirectAttributes redirectAttributes, Principal principal, MultipartFile file,
            Model viewmodel) {
        if (result.hasErrors()) {
            viewmodel.addAttribute("title", "Aggiorna articolo");

            article.setImage(articleService.read(id).getImage());
            viewmodel.addAttribute("article", article);
            viewmodel.addAttribute("categories", categoryService.readAll());
            return "article/edit";
        }

        articleService.update(id, article, file);
        redirectAttributes.addFlashAttribute("successMessage", "Articolo modificato correttamente");
        return "redirect:/articles";
    }

    @GetMapping("/delete/{id}")
    public String articleDelete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        articleService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Articolo eliminato con successo");
        return "redirect:/articles";
    }

    @GetMapping("revisor/detail/{id}")
    public String revisorDetailArticle(@PathVariable("id") Long id, Model viewModel) {
        viewModel.addAttribute("title", "Dettagli articolo");
        viewModel.addAttribute("article", articleService.read(id));
        return "revisor/detail";
    }

    @PostMapping("revisor/accept")
    public String articleSetAccepted(@RequestParam("action") String action, @RequestParam("articleId") Long articleId,
            RedirectAttributes redirectAttributes) {
        if (action.equals("accept")) {
            articleService.setIsAccepted(true, articleId);
            redirectAttributes.addFlashAttribute("resultMessage", "Articolo accettato con successo");
        } else if (action.equals("reject")) {
            articleService.setIsAccepted(false, articleId);
            redirectAttributes.addFlashAttribute("resultMessage", "Articolo rifiutato");
        } else {
            redirectAttributes.addFlashAttribute("resultMessage", "Azione non corretta");
        }
        return "redirect:/revisor/dashboard";
    }

    @GetMapping("/search")
    public String articleSearch(@Param("keyword") String keyword, Model viewModel) {
        viewModel.addAttribute("title", "Tutti gli articoli trovati");
        List<ArticleDto> articles = articleService.search(keyword);
        List<ArticleDto> acceptedArticles = articles.stream()
                .filter(article -> Boolean.TRUE.equals(article.getIsAccepted())).collect(Collectors.toList());
        viewModel.addAttribute("articles", acceptedArticles);
        return "articles/articles";
    }
}
