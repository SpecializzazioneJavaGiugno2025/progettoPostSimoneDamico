package it.simo.aulab_post.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.simo.aulab_post.models.CareerRequest;
import it.simo.aulab_post.models.Role;
import it.simo.aulab_post.models.User;
import it.simo.aulab_post.repositories.CareerRequestRepository;
import it.simo.aulab_post.repositories.RoleRepository;
import it.simo.aulab_post.repositories.UserRepository;

@Service
public class CareerRequestServiceImpl implements CareerRequestService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CareerRequestRepository careerRequestRepository;

    @Override
    public boolean isRoleAlreadyAssigned(User user, CareerRequest careerRequest) {
        List<Long> allUserIds=careerRequestRepository.findAllUserIds();
        if(!allUserIds.contains(user.getId())){
            return false;
        }

        List<Long> requests=careerRequestRepository.findByUserId(user.getId());
        return requests.stream().anyMatch(roleId->roleId.equals(careerRequest.getRole().getId()));
    }

    @Override
    public void save(CareerRequest careerRequest, User user) {
       careerRequest.setUser(user);
       careerRequest.setChecked(false);
       careerRequestRepository.save(careerRequest);

       emailService.sendSimpleEmail("admin@aulab.it", "richiesta per ruolo: " + careerRequest.getRole().getName(),"C'è una nuova richieta da parte di "+user.getUsername());
    }

    @Override
    public void careerAccept(Long requestId) {
        CareerRequest request=careerRequestRepository.findById(requestId).get();
        User user=request.getUser();
        Role role=request.getRole();

        List<Role> rolesUser=user.getRoles();
        Role newRole=roleRepository.findByName(role.getName());
        rolesUser.add(newRole);

        user.setRoles(rolesUser);
        userRepository.save(user);
        request.setChecked(true);
        careerRequestRepository.save(request);

        emailService.sendSimpleEmail(user.getEmail(),"Ruolo abilitato","Ciao, la tua richiesta di collaborazione è stata accettata");
        
    }

    @Override
    public CareerRequest find(Long id) {
        return careerRequestRepository.findById(id).get();
    }

}
