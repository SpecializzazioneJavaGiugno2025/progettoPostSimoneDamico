package it.simo.aulab_post.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.simo.aulab_post.dtos.ArticleDto;
import it.simo.aulab_post.dtos.UserDto;
import it.simo.aulab_post.models.Article;
import it.simo.aulab_post.models.User;
import it.simo.aulab_post.repositories.ArticleRepository;
import it.simo.aulab_post.repositories.CareerRequestRepository;
import it.simo.aulab_post.services.ArticleService;
import it.simo.aulab_post.services.CategoryService;
import it.simo.aulab_post.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CareerRequestRepository careerRequestRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public String home(Model viewModel) {

        List<ArticleDto> articles = new ArrayList<>();
        for(Article article: articleRepository.findByIsAcceptedTrue()) {
            articles.add(modelMapper.map(article, ArticleDto.class));
        }
        Collections.sort(articles,Comparator.comparing(ArticleDto::getPublishDate).reversed());
        List<ArticleDto> lastArticles =articles.stream().filter(article-> Boolean.TRUE.equals(article.getIsAccepted())).limit(3).collect(Collectors.toList());
        viewModel.addAttribute("articles", lastArticles);

        return "home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "auth/register";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model,
            RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
                User existingUser = userService.findUserByEmail(userDto.getEmail());
                if(existingUser != null && existingUser.getEmail()!= null && !existingUser.getEmail().isEmpty()) {
                    result.rejectValue("email", null, "Email già utilizzata");
                }
                if(result.hasErrors()) {
                    model.addAttribute("user", userDto);
                    return "auth/register";
                }
                userService.saveUser(userDto, redirectAttributes, request, response);

                redirectAttributes.addFlashAttribute("success", "Registrazione avvenuta con successo");
                return "redirect:/";
    }

    @GetMapping("/search/{id}")
    public String userArticlesSearch(@PathVariable("id") Long id, Model viewModel) {
        User user= userService.find(id);
        viewModel.addAttribute("title", "Tutti gli articoli trovati per utente: "+user.getUsername());

        List<ArticleDto> articles = articleService.searchByAuthor(user);
                List<ArticleDto> acceptedArticles=articles.stream().filter(article-> Boolean.TRUE.equals(article.getIsAccepted())).collect(Collectors.toList());

        viewModel.addAttribute("articles",acceptedArticles);
        return "article/articles";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model viewModel){
        viewModel.addAttribute("title", "Richieste ricevute");
        viewModel.addAttribute("requests",careerRequestRepository.findByIsCheckedFalse());
        viewModel.addAttribute("categories",categoryService.readAll());
        return "admin/dashboard";
    }

    @GetMapping("/revisor/dashboard")
    public String revisorDashboard(Model viewModel){
        viewModel.addAttribute("title", "Articoli da revisionare");
        viewModel.addAttribute("articles",articleRepository.findByIsAcceptedIsNull());
        return "revisor/dashboard";
    }

    @GetMapping("/writer/dashboard")
    public String writerDashboard(Model viewModel, Principal principal){
        viewModel.addAttribute("title", "I tuoi articoli");
        List<ArticleDto> userArticles= articleService.readAll().stream().filter(article-> article.getUser().getEmail().equals(principal.getName())).toList();

        viewModel.addAttribute("articles", userArticles);
        return "writer/dashboard";
    }
}
