package event.recommendation.system.integration.web;

import event.recommendation.system.controllers.user.UserController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application.test.properties")
@Sql(value = {"/sql/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserController userController;

    @Test
    @WithUserDetails("root@root.root")
    public void shouldBeAuthenticatedForAdminRole() throws Exception {
        this.mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(authenticated());
    }

    @Test
    @WithUserDetails("user@user.user")
    public void shouldBeDeniedUserRole() throws Exception {
        this.mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
