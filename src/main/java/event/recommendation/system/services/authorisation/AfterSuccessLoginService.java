package event.recommendation.system.services.authorisation;

import event.recommendation.system.entities.user.User;
import event.recommendation.system.managers.UserManager;
import event.recommendation.system.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AfterSuccessLoginService {
    private final UserService userService;

    public String redirectIfUserFirstLogin() {
        User loggedInUser = new UserManager().getLoggedInUser();
        if(userService.isFirstLogin(loggedInUser)) {
            return redirectToChoosePreferences(loggedInUser);
        }
        return  "redirect:/";
    }

    private String redirectToChoosePreferences(User user) {
        user.setFirstLogin(false);
        userService.save(user);
        return "redirect:/userPreferences/" + user.getId();
    }

}
