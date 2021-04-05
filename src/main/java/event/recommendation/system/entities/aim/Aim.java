package event.recommendation.system.entities.aim;

import event.recommendation.system.entities.time.data.Time;
import event.recommendation.system.entities.user.User;
import event.recommendation.system.enums.State;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity(name = "aim")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Aim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank
    private String title;
    @Column
    @NotBlank
    private String description;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String text;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String specify;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String measurable;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String attainable;
    @Column(columnDefinition = "varchar(255)")
    @NotBlank
    private String relevant;
    @Column
    private Date timeBased;
    @Column
    @NotNull
    private String aimState = State.Aim.NEW.toString();
    @Column
    @NotNull
    private Date creationDate;
    @Column
    private Date modificationDate;
    @Column
    private Date deletionDate;
    @Column
    private Date dateFrom;
    @Column
    private Date dateTo;
    @Column(name = "achieved_date")
    private Date achievedDate;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "aim", cascade=CascadeType.ALL, targetEntity=Time.class, fetch=FetchType.EAGER)
    @Column(name = "logged_time")
    private Set<Time> loggedTime;

    @Override
    public String toString() {
        return "Aim{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
