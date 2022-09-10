package event.recommendation.system.subscription.entity;

import event.recommendation.system.enums.SubscriptionType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private SubscriptionType type;
}
