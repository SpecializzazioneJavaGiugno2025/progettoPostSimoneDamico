package it.simo.aulab_post.services;

import it.simo.aulab_post.models.Article;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.multipart.MultipartFile;
public interface ImageService {
    void saveImageOnDb(String url, Article article);
    CompletableFuture<String> saveImageOnCloud(MultipartFile file) throws Exception;
    void deleteImage(String imagePath) throws IOException;
}
