package event.recommendation.system.repositories;

import event.recommendation.system.entities.aim.Aim;
import event.recommendation.system.entities.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AimRepository extends CrudRepository<Aim, Long> {
    List<Aim> findByUser(User user);
    List<Aim> findAimsByAimStateAndUser(String aimState, User user);
    List<Aim> findAimsByAimStateIsNotLikeAndUser(String aimStateNotLike, User user);
}
