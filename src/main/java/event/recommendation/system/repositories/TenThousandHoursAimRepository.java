package event.recommendation.system.repositories;

import event.recommendation.system.entities.aim.TenThousandHoursAim;
import event.recommendation.system.entities.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenThousandHoursAimRepository extends CrudRepository<TenThousandHoursAim, Long> {
    List<TenThousandHoursAim> findByUser(User user);
    List<TenThousandHoursAim> findTenThousandHoursAimsByAimStateAndUser(String aimState, User user);
    List<TenThousandHoursAim> findAimsByAimStateIsNotLikeAndUser(String aimStateNotLike, User user);
}
