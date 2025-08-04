package it.simo.aulab_post.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import it.simo.aulab_post.models.Comment;
import it.simo.aulab_post.repositories.ArticleRepository;
import it.simo.aulab_post.repositories.CommentRepository;
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

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public String articlesIndex(Model viewModel, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage=page.orElse(1);
        int pageSize=size.orElse(6);
        viewModel.addAttribute("title", "Tutti gli articoli");
        // List<ArticleDto> articles = new ArrayList<>();
        // for (Article article : articleRepository.findByIsAcceptedTrue()) {
        //     articles.add(modelMapper.map(article, ArticleDto.class));
        // }
        Page<ArticleDto> articles=articleService.findPaginated(PageRequest.of(currentPage-1, pageSize,Sort.by("pubishDate").descending()));
        // Collections.sort(articles, Comparator.comparing(ArticleDto::getPublishDate).reversed());
        viewModel.addAttribute("articles", articles);
        int totalPages=articles.getTotalPages();
        if(totalPages>0){
            List<Integer> pageNumbers =IntStream.rangeClosed(1, totalPages).boxed()
            .collect(Collectors.toList());
            viewModel.addAttribute("pageNumbers",pageNumbers);
        }
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
        List<Comment> comments = commentRepository.findByArticle(articleRepository.findById(id).get());
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
