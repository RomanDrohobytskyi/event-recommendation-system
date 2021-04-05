package application.integration.services;

import event.recommendation.system.entities.aim.Aim;
import event.recommendation.system.enums.State;
import event.recommendation.system.repositories.AimRepository;
import event.recommendation.system.services.aim.SmartAimService;
import event.recommendation.system.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AimServiceIntegrationTest {

    @Mock
    private UserService userService;
    @Mock
    private AimRepository aimRepository;
    @InjectMocks
    private SmartAimService aimService;
    private Aim aim;

    @BeforeEach
    public void initializeAim(){
        aim = new Aim();
        aim.setTitle("Title");
        aim.setText("Text");
        aim.setDescription("Description");
        aim.setSpecify("Specify");
        aim.setMeasurable("Measurable");
        aim.setAttainable("Attainable");
        aim.setRelevant("Relevant");
        aim.setCreationDate(new Date());
        aim.setAimState(State.Aim.NEW.getState());

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveAim() {
        given(aimRepository.save(any(Aim.class))).willReturn(aim.toBuilder().id(1L).build());

        Aim savedAim = aimService.save(aim);

        then(savedAim.getId()).isNotNull();
        then(savedAim.getId()).isEqualTo(1L);
        assertThat(State.Aim.NEW.getState()).isEqualTo(savedAim.getAimState());
    }

    @Test
    void shouldDeleteAim(){
        given(aimRepository.save(any(Aim.class))).willReturn(aim.toBuilder().id(1L).build());

        Aim deleted = aimService.delete(aim);

        //then
        assertThat(State.Aim.DELETED.getState()).isEqualTo(deleted.getAimState());
    }
}
