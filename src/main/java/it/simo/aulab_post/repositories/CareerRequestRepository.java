package it.simo.aulab_post.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.simo.aulab_post.models.CareerRequest;

public interface CareerRequestRepository extends CrudRepository<CareerRequest, Long> {
    List<CareerRequest> findByIsCheckedFalse();

    @Query(value="SELECT user_id FROM users_roles",nativeQuery=true)
    List<Long> findAllUserIds();

    @Query(value="SELECT role_id FROM users_roles WHERE user_id= :id",nativeQuery=true)
    List<Long> findByUserId(@Param("id") Long id);
}
