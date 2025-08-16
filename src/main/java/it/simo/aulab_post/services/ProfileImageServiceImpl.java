package it.simo.aulab_post.services;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import it.simo.aulab_post.models.ProfileImage;
import it.simo.aulab_post.models.User;
import it.simo.aulab_post.repositories.ProfileImageRepository;
import it.simo.aulab_post.utils.StringManipulation;

@Service
public class ProfileImageServiceImpl implements ProfileImageService{

    @Autowired
    private ProfileImageRepository profileImageRepository;

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.key}")
    private String supabaseKey;

    @Value("${supabase.bucket}")
    private String supabaseBucket;

    @Value("${supabase.image}")
    private String supabaseImage;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void saveImageOnDb(String url, User user) {
        url= url.replace(supabaseBucket, supabaseImage);
        profileImageRepository.save(ProfileImage.builder().path(url).user(user).build());
    }

    @Async
    public CompletableFuture<String> saveImageOnCloud(MultipartFile file) throws Exception {
         if(!file.isEmpty()){
            try{
                String nameFile= UUID.randomUUID().toString()+"-"+ file.getOriginalFilename();
                String extension =StringManipulation.getFileExtension(nameFile);
                String url= supabaseUrl + supabaseBucket+ nameFile;
                MultiValueMap<String,Object> body= new LinkedMultiValueMap<>();
                body.add("file",file.getBytes());

                HttpHeaders headers= new HttpHeaders();
                headers.set("Content-Type","image/"+extension);
                headers.set("Authorization","Bearer "+supabaseKey);

                HttpEntity<byte[]> requestEntity= new HttpEntity<>(file.getBytes(),headers);
                restTemplate.exchange(url,HttpMethod.POST, requestEntity,String.class);
                return CompletableFuture.completedFuture(url);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            throw new IllegalArgumentException("File is empty");
        }

        return CompletableFuture.failedFuture(null);
    }

    @Override
    public void deleteImage(String imagePath) throws IOException {
        String url=imagePath.replace(supabaseImage, supabaseBucket);
        profileImageRepository.deleteByPath(imagePath);
        RestTemplate restTemplate=new RestTemplate();

        HttpHeaders headers =new HttpHeaders();
        headers.set("Authorization","Bearer "+supabaseKey);
        HttpEntity<String> entity= new HttpEntity<>(headers);
        ResponseEntity<String> response=restTemplate.exchange(url,HttpMethod.DELETE,entity,String.class);
    }

}
