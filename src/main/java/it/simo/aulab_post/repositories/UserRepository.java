package it.simo.aulab_post.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.simo.aulab_post.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
}
