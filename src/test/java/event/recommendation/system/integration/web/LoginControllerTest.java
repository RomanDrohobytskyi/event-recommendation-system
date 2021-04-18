package event.recommendation.system.integration.web;

import event.recommendation.system.controllers.authorisation.login.LoginController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application.test.properties")
@Sql(value = {"/sql/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LoginController loginController;

    @Test
    public void shouldLoadContext() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("LOGIN")));
    }

    @Test
    public void shouldRedirectForNotLoggedUser() throws Exception {
        this.mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(("http://localhost/login")));
    }

    @Test
    public void shouldCorrectLoginUserTest() throws Exception {
        this.mockMvc.perform(formLogin()
                .user("root@root.root")
                .password("111"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/afterSuccessLogin"));
    }

    @Test
    public void shouldNotLoginUser() throws Exception {
        this.mockMvc.perform(formLogin()
                .user("root@root.root")
                .password("222"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/loginError"));
    }

}