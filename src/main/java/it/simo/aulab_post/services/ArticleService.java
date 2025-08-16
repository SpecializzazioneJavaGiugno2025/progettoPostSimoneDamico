package it.simo.aulab_post.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import it.simo.aulab_post.dtos.ArticleDto;
import it.simo.aulab_post.models.Article;
import it.simo.aulab_post.models.Category;
import it.simo.aulab_post.models.Comment;
import it.simo.aulab_post.models.User;
import it.simo.aulab_post.repositories.ArticleRepository;
import it.simo.aulab_post.repositories.UserRepository;

@Service
public class ArticleService implements CrudService<ArticleDto, Article, Long> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ImageService imageService;

    @Override
    public List<ArticleDto> readAll() {
        List<ArticleDto> dtos = new ArrayList<>();
        for (Article article : articleRepository.findAll()) {
            dtos.add(modelMapper.map(article, ArticleDto.class));
        }
        return dtos;
    }

    @Override
    public ArticleDto read(Long key) {
        Optional<Article> optArticle = articleRepository.findById(key);
        if (optArticle.isPresent()) {
            return modelMapper.map(optArticle.get(), ArticleDto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Articolo non trovato");
        }

    }

    @Override
    public ArticleDto create(Article article, Principal principal, MultipartFile file) {
        String url = "";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = (userRepository.findById(userDetails.getId())).get();
            article.setUser(user);
        }
        if (!file.isEmpty()) {
            try {
                CompletableFuture<String> futureUrl = imageService.saveImageOnCloud(file);
                url = futureUrl.get();
            } catch (Exception e) {
            }
        }

        article.setIsAccepted(null);
        ArticleDto dto = modelMapper.map(articleRepository.save(article), ArticleDto.class);

        if (!file.isEmpty()) {
            imageService.saveImageOnDb(url, article);
        }
        return dto;
    }

    @Override
    public ArticleDto update(Long key, Article updateArticle, MultipartFile file) {
        String url = "";
        updateArticle.setId(key);
        Article article = articleRepository.findById(key).get();
        updateArticle.setUser(article.getUser());

        if (!file.isEmpty()) {
            try {
                imageService.deleteImage(article.getImage().getPath());
                try {
                    CompletableFuture<String> futureUrl = imageService.saveImageOnCloud(file);
                    url = futureUrl.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                imageService.saveImageOnDb(url, updateArticle);

                updateArticle.setIsAccepted(null);
                return modelMapper.map(articleRepository.save(updateArticle), ArticleDto.class);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (article.getImage() == null) {
            updateArticle.setIsAccepted(null);
        } else {
            updateArticle.setImage(article.getImage());
            if (updateArticle.equals(article) == false) {
                updateArticle.setIsAccepted(null);
            } else {
                updateArticle.setIsAccepted(article.getIsAccepted());
            }
            return modelMapper.map(articleRepository.save(updateArticle), ArticleDto.class);

        }
        return null;

    }

    @Override
    public void delete(Long key) {
        if (articleRepository.existsById(key)) {
            Article article = articleRepository.findById(key).get();
            try {
                String path = article.getImage().getPath();
                article.getImage().setArticle(null);
                imageService.deleteImage(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
            articleRepository.deleteById(key);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public List<ArticleDto> searchByCategory(Category category) {
        List<ArticleDto> dtos = new ArrayList<>();
        for (Article article : articleRepository.findByCategory(category)) {
            dtos.add(modelMapper.map(article, ArticleDto.class));
        }
        return dtos;
    }

    public List<ArticleDto> searchByAuthor(User user) {
        List<ArticleDto> dtos = new ArrayList<>();
        for (Article article : articleRepository.findByUser(user)) {
            dtos.add(modelMapper.map(article, ArticleDto.class));
        }
        return dtos;
    }

    public void setIsAccepted(Boolean bool, Long id) {
        Article article = articleRepository.findById(id).get();
        article.setIsAccepted(bool);
        articleRepository.save(article);
    }

    public List<ArticleDto> search(String keyword) {
        List<ArticleDto> dtos = new ArrayList<>();
        for (Article article : articleRepository.search(keyword)) {
            dtos.add(modelMapper.map(article, ArticleDto.class));
        }
        return dtos;
    }

    public Page<ArticleDto> findPaginated(Pageable pageable){
        List<ArticleDto> articles=new ArrayList<>();
        int pageSize=pageable.getPageSize();
        int currentPage=pageable.getPageNumber();
        int startItem=currentPage*pageSize;
        List<ArticleDto> list=new ArrayList<>();
        for(Article article: articleRepository.findByIsAcceptedTrue()){
            articles.add(modelMapper.map(article, ArticleDto.class));
        }
        

        for(Article article :articleRepository.findByIsAcceptedTrue()){
            list.add(modelMapper.map(article, ArticleDto.class));
        }
        if(list.size()<startItem){
            list=Collections.emptyList();
        }else{
            int toIndex=Math.min(startItem + pageSize,list.size());
            list=articles.subList(startItem,toIndex);
        }

        Page<ArticleDto> articlePage=new PageImpl<ArticleDto>(list, PageRequest.of(currentPage, pageSize),articles.size());

        return articlePage;
    }

    public void addComment(Long id, Comment comment, Principal principal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addComment'");
    }

    public void addLike(Long id, Principal principal) {
       Article article=articleRepository.findById(id).get();
       
    }

}
