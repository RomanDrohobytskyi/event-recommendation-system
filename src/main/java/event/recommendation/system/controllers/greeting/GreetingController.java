package event.recommendation.system.controllers.greeting;

import event.recommendation.system.menu.MenuTabs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GreetingController {

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("menuElements", MenuTabs.getInstance().getDefaultMenu());
        model.addAttribute("slideMenuElements", MenuTabs.getInstance().getDefaultSlideMenu());
        return "greeting";
    }

}
