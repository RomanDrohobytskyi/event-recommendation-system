package event.recommendation.system.integration.web;

import event.recommendation.system.controllers.event.EventsMainController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("root@root.root")
public class EventsMainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EventsMainController eventsMainController;

    @Test
    public void shouldLoadEventsMainPage() throws Exception {
        this.mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(authenticated());
                //.andExpect(xpath("//*[@id='profile']/a").string("title='root'"));
    }
}
