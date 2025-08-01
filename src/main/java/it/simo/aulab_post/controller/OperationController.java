package it.simo.aulab_post.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.simo.aulab_post.models.CareerRequest;
import it.simo.aulab_post.models.Role;
import it.simo.aulab_post.models.User;
import it.simo.aulab_post.repositories.RoleRepository;
import it.simo.aulab_post.repositories.UserRepository;
import it.simo.aulab_post.services.CareerRequestService;

@Controller
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CareerRequestService careerRequestService;


    @GetMapping("/career/request")
    public String careerRequestCreate(Model viewModel){
        viewModel.addAttribute("title", "Inserisci la tua richiesta");
        viewModel.addAttribute("careerRequest",new CareerRequest());

        List<Role> roles=roleRepository.findAll();

        roles.removeIf(e ->e.getName().equals("ROLE_USER"));
        viewModel.addAttribute("roles",roles);

        return "career/requestForm";
    }
    
    @PostMapping("/career/request/save")
    public String careerRequestStore(@ModelAttribute("careerRequest") CareerRequest careerRequest, Principal principal, RedirectAttributes redirectAttributes){
        User user=userRepository.findByEmail(principal.getName());
        if(careerRequestService.isRoleAlreadyAssigned(user,careerRequest)){
            redirectAttributes.addFlashAttribute("errorMessage", "Questo ruolo ti è già stato assegnato");
            return "redirect:/";
        }

        careerRequestService.save(careerRequest,user);

        redirectAttributes.addFlashAttribute("successMessage","Richiesta inviata con successo");
        return "redirect:/";
    }

    @GetMapping("/career/request/detail/{id}")
    public String careerRequestDetail(@PathVariable Long id, Model viewModel){
        viewModel.addAttribute("title", "Dettagli richiesta");
        viewModel.addAttribute("request",careerRequestService.find(id));
        return "career/requestDetail";
    }

    @PostMapping("/career/request/accept/{requestId}")
    public String careerRequestAccepted(@PathVariable Long requestId, RedirectAttributes redirectAttributes){
        careerRequestService.careerAccept(requestId);
        redirectAttributes.addFlashAttribute("successMessage","Richiesta accettata con successo");
        return "redirect:/admin/dashboard";
    }
}
