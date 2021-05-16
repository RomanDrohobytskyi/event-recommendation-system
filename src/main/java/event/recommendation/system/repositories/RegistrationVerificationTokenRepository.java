package event.recommendation.system.repositories;

import event.recommendation.system.entities.RegistrationVerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationVerificationTokenRepository extends CrudRepository<RegistrationVerificationToken, Long> {
    Optional<RegistrationVerificationToken> getByToken(String token);
}
