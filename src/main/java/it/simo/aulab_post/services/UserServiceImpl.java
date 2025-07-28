package it.simo.aulab_post.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.simo.aulab_post.dtos.UserDto;
import it.simo.aulab_post.models.User;
import it.simo.aulab_post.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() ;
    };

    @Override
    public void saveUser(UserDto userDto, RedirectAttributes redirectAttributes, HttpServletRequest request,
            HttpServletResponse response) {
       User user = new User();
       user.setUsername(userDto.getFirstName() + " " + userDto.getLastName());
       user.setEmail(userDto.getEmail());
       user.setPassword(passwordEncoder().encode(userDto.getPassword()));
       userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
