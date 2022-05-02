package event.recommendation.system.controllers.authorisation.login;

import event.recommendation.system.services.builders.ModelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final ModelBuilder modelBuilder;

    @GetMapping("/login")
    public String login(Model model) {
        modelBuilder.builder(model).withLoginMenu();
        return "login";
    }

    @GetMapping("/loginError")
    public String loginError(Model model) {
        modelBuilder.builder(model)
                .withLoginMenu()
                .withStringParam("validatorError", "Wrong email or password, try again.");
        return "login";
    }
}
