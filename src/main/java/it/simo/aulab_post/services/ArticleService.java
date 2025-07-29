package it.simo.aulab_post.services;

import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.simo.aulab_post.dtos.ArticleDto;
import it.simo.aulab_post.models.Article;
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
    @Override
    public List<ArticleDto> readAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArticleDto read(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArticleDto create(Article article, Principal principal, MultipartFile file) {
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null) {
            CustomUserDetails userDetails= (CustomUserDetails) authentication.getPrincipal();
            User user=(userRepository.findById(userDetails.getId())).get();
            article.setUser(user);
        }
        ArticleDto dto= modelMapper.map(articleRepository.save(article), ArticleDto.class);
        return dto;
    }

    @Override
    public ArticleDto update(Long key, Article model, MultipartFile file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
