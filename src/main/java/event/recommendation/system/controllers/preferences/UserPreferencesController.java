package event.recommendation.system.controllers.preferences;

import event.recommendation.system.entities.User;
import event.recommendation.system.services.user.preferences.UserPreferencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserPreferencesController {
    private final UserPreferencesService userPreferencesService;

    @GetMapping("/userPreferences")
    public String userPreferences(Model model) {
        userPreferencesService.addUserAndMenu(model);
        return "userPreferences";
    }

    @PostMapping("/userPreferences/save")
    public String userEditedSave(
            @RequestParam User user,
            @RequestParam Map<String, String> form) {
        userPreferencesService.saveUserPreferences(user, form);
        return "redirect:/";
    }
}
