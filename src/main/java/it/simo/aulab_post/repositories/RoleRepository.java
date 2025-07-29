package it.simo.aulab_post.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.simo.aulab_post.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
