package event.recommendation.system.controllers.aims;

import event.recommendation.system.menu.MenuTabs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllAimsController {

    @GetMapping("/aims")
    public String allAims(Model model){
        model.addAttribute("menuElements", MenuTabs.defaultMenu());
        model.addAttribute("slideMenuElements", MenuTabs.defaultSlideMenu());
        return "aims";
    }
}
