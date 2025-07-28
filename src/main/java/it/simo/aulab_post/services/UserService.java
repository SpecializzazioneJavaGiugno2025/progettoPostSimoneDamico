package it.simo.aulab_post.services;



import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.simo.aulab_post.dtos.UserDto;
import it.simo.aulab_post.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

    void saveUser(UserDto userDto,RedirectAttributes redirectAttributes,HttpServletRequest request,
    HttpServletResponse response);
    User findUserByEmail(String email);
}
