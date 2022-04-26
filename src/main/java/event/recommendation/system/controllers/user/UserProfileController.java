package event.recommendation.system.controllers.user;

import event.recommendation.system.entities.User;
import event.recommendation.system.services.user.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping("/userProfile")
    public String getUserProfile(Model model){
        userProfileService.initModel(model);
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
        userProfileService.initModel(model);
        return "userProfile";
    }

    @PostMapping("/userProfile/subscribe")
    public String subscribeToNotifications(@RequestParam User user,
                                           @RequestParam Map<String, String> form,
                                           Model model) {
        userProfileService.subscribe(user, form);
        userProfileService.initModel(model);
        return "userProfile";
    }

}
