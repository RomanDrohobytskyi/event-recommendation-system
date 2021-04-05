package event.recommendation.system.entities.aim;

import event.recommendation.system.entities.time.data.TenThousandHoursAimTime;
import event.recommendation.system.entities.user.User;
import event.recommendation.system.enums.State;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity(name = "ten_thousand_hours_aim")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TenThousandHoursAim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    @Size(min = 3, max = 32)
    private String title;
    @Column
    @NotNull
    private String description;
    @Column(columnDefinition = "varchar(255) default ''")
    @NotNull
    private String text;
    @Column
    @NotNull
    private String aimState = State.Aim.NEW.toString();
    @NotNull
    @CreationTimestamp
    private Date creationDate;
    @Column
    private Date modificationDate;
    @Column
    private Date deletionDate;
    @Column(name = "achieved_date")
    private Date achievedDate;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "aim", targetEntity = TenThousandHoursAimTime.class, fetch = FetchType.EAGER)
    @Column(name = "logged_time")
    private Set<TenThousandHoursAimTime> loggedTime;

}