package it.simo.aulab_post.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.simo.aulab_post.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
