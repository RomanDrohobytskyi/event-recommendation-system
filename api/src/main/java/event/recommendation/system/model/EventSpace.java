package event.recommendation.system.model;

import event.recommendation.system.common.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventSpace extends BaseEntity {
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String address;
    @NotNull
    @Column(name = "zip_code")
    private String zipCode;
    @OneToOne(mappedBy = "space")
    private Event event;
}
