package it.simo.aulab_post.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.simo.aulab_post.models.Article;
import it.simo.aulab_post.models.ProfileImage;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email è obbligatoria")
    @Email
    private String email;
    @NotEmpty(message = "Password è obbligatoria")
    private String password;
    @NotEmpty(message = "Password conferma è obbligatoria")
    private String passwordConfirm;
    private ProfileImage profileImage;
    private List<Article>  favoritedArticles = new ArrayList<>();

    


@AssertTrue(message = "Le password non corrispondono")
public boolean isPasswordConfirmed() {
    if (password == null || passwordConfirm == null) {
        return true; // Non validare finché l’utente non compila entrambi i campi
    }
    return Objects.equals(password, passwordConfirm);
}


}
