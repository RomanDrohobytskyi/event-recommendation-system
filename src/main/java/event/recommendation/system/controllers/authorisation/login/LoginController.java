package event.recommendation.system.controllers.authorisation.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("validatorError", "Wrong email or password, try again.");
        return "login";
    }
}
