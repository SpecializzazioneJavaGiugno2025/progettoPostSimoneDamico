package it.simo.aulab_post.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.simo.aulab_post.models.ProfileImage;
import it.simo.aulab_post.models.User;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long>{
    @Modifying
    @Query(value="delete from profile_images where path = :path", nativeQuery=true)
    void deleteByPath(@Param("path") String path);
    ProfileImage findByUser(User user);
}
