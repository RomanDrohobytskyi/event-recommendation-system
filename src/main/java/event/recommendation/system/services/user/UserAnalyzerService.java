package event.recommendation.system.services.user;

import event.recommendation.system.entities.User;
import event.recommendation.system.managers.UserManager;
import event.recommendation.system.menu.MenuTabs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class UserAnalyzerService {
    private final UserManager userManager = new UserManager();

    public void addAnalyzerData(Model model) {
        User loggedInUser = userManager.getLoggedInUser();
        model.addAttribute("user", loggedInUser);
        model.addAttribute("menuElements", MenuTabs.getInstance().getDefaultMenu());
        model.addAttribute("slideMenuElements", MenuTabs.getInstance().getDefaultSlideMenu());
    }

}
