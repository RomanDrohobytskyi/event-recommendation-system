package event.recommendation.system.controllers.greeting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static event.recommendation.system.menu.MenuTabs.getDefaultMenu;
import static event.recommendation.system.menu.MenuTabs.getDefaultSlideMenu;

@Controller
@RequiredArgsConstructor
public class GreetingController {

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("menuElements", getDefaultMenu());
        model.addAttribute("slideMenuElements", getDefaultSlideMenu());
        return "greeting";
    }
}
