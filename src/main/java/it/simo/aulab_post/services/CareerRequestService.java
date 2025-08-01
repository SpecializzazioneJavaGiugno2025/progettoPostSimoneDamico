package it.simo.aulab_post.services;

import it.simo.aulab_post.models.CareerRequest;
import it.simo.aulab_post.models.User;

public interface CareerRequestService {
    boolean isRoleAlreadyAssigned(User user,CareerRequest careerRequest);
    void save(CareerRequest careerRequest, User user);
    void careerAccept(Long requestId);
    CareerRequest find(Long id);
}
