package it.simo.aulab_post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import it.simo.aulab_post.dtos.UserDto;
import it.simo.aulab_post.models.User;
import it.simo.aulab_post.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "auth/register";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model,
            RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
                User existingUser = userService.findUserByEmail(userDto.getEmail());
                if(existingUser != null && existingUser.getEmail()!= null && !existingUser.getEmail().isEmpty()) {
                    result.rejectValue("email", null, "Email già utilizzata");
                }
                if(result.hasErrors()) {
                    model.addAttribute("user", userDto);
                    return "auth/register";
                }
                userService.saveUser(userDto, redirectAttributes, request, response);

                redirectAttributes.addFlashAttribute("success", "Registrazione avvenuta con successo");
                return "redirect:/register?success";
    }
}
