package it.simo.aulab_post.services;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.multipart.MultipartFile;

import it.simo.aulab_post.models.User;

public interface ProfileImageService {
    void saveImageOnDb(String url, User user);
    CompletableFuture<String> saveImageOnCloud(MultipartFile file) throws Exception;

    void deleteImage(String imagePath) throws IOException;
}
