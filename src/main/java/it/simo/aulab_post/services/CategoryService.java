package it.simo.aulab_post.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.simo.aulab_post.dtos.CategoryDto;
import it.simo.aulab_post.models.Category;
import it.simo.aulab_post.repositories.CategoryRepository;

@Service
public class CategoryService implements CrudService<CategoryDto, Category, Long> {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDto> readAll() {
       List<CategoryDto> dtos = new ArrayList<CategoryDto>();
       for(Category category: categoryRepository.findAll()) {
           dtos.add(modelMapper.map(category, CategoryDto.class));
       }
       return dtos;
    }

    @Override
    public CategoryDto read(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CategoryDto create(Category model, Principal principal, MultipartFile file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CategoryDto update(Long key, Category model, MultipartFile file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}


