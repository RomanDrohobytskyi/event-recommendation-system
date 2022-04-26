package event.recommendation.system.repositories;

import event.recommendation.system.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findUserByEmail(String email);
    Optional<User> findByActivationCode(String code);
    @Query(value = "SELECT * FROM user WHERE email = ?1 AND active AND activation_code IS NULL", nativeQuery = true)
    User findActiveUserWithoutActivationCodeByEmail(String email);

    @Query(value = "SELECT * FROM user u " +
            "INNER JOIN user_subscriptions us " +
            "on u.id = us.user_id " +
            "WHERE us.subscription_id in (?1) ", nativeQuery = true)
    Set<User> findBySubscriptions(Set<Long> subscriptionIds);
}


