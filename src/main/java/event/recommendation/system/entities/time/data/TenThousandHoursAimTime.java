package event.recommendation.system.entities.time.data;

import event.recommendation.system.entities.aim.TenThousandHoursAim;
import event.recommendation.system.enums.State;
import event.recommendation.system.models.ConvertedDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "ten_thousand_hours_aim_time")
@Getter
@Setter
@NoArgsConstructor
public class TenThousandHoursAimTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "time")
    private Double time;
    @NotNull
    @Column(name = "date")
    private java.util.Date date;
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "creation_date")
    private java.util.Date creationDate;
    @Column(name = "modification_date")
    private java.util.Date modificationDate;
    @Column(name = "state")
    private String state = State.Date.NEW.toString();
    @ManyToOne
    private TenThousandHoursAim aim;

    public ConvertedDate getConvertedDate(TenThousandHoursAimTime time){
        return new ConvertedDate(time.getDate());
    }
}
