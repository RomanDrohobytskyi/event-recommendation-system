package event.recommendation.system.notifications.entity;


import event.recommendation.system.common.BaseEntity;
import event.recommendation.system.entities.User;
import event.recommendation.system.notifications.model.NotificationState;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification extends BaseEntity {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private LocalDateTime creationDate;
    @NotNull
    @Enumerated(EnumType.STRING)
    private NotificationState state;
    @ManyToMany(mappedBy = "notifications", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<User> receivers;
}
