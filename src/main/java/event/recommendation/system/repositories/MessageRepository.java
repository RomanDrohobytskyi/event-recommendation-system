package event.recommendation.system.repositories;

import event.recommendation.system.entities.message.Message;
import event.recommendation.system.entities.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByTagAndAndUser(String tag, User user);
    List<Message> findByUser(User user);
}
