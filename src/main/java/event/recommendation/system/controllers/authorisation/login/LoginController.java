package event.recommendation.system.controllers.authorisation.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static event.recommendation.system.menu.MenuTabs.getLoginMenu;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("menuElements", getLoginMenu());
        return "login";
    }

    @GetMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("menuElements", getLoginMenu());
        model.addAttribute("validatorError", "Wrong email or password, try again.");
        return "login";
    }
}
