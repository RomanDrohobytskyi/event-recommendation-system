package event.recommendation.system.controllers.user;

import event.recommendation.system.entities.User;
import event.recommendation.system.managers.UserManager;
import event.recommendation.system.menu.MenuTabs;
import event.recommendation.system.services.user.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final UserManager userManager = new UserManager();

    @GetMapping("/userProfile")
    public String getUserProfile(Model model){
        model.addAttribute("user", userManager.getLoggedInUser());
        addMenuElements(model);
        return "userProfile";
    }

    @PostMapping("/userProfile/save")
    public String saveUserProfileChanges(@RequestParam(name = "avatar", required = false) MultipartFile avatar,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String username,
            @RequestParam("userId") User user,
            Model model){

        userProfileService.adaptAndSaveEditedUserProfile(avatar, firstName, lastName, username, user);
        model.addAttribute("user", user);
        addMenuElements(model);
        return "userProfile";
    }

    private void addMenuElements(Model model){
        model.addAttribute("menuElements", MenuTabs.getInstance().getDefaultMenu());
        model.addAttribute("slideMenuElements", MenuTabs.getInstance().getDefaultSlideMenu());
    }

}
