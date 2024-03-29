package event.recommendation.system.controllers.user;

import event.recommendation.system.entities.User;
import event.recommendation.system.roles.Role;
import event.recommendation.system.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static event.recommendation.system.menu.MenuTabs.getDefaultMenu;
import static event.recommendation.system.menu.MenuTabs.getDefaultSlideMenu;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private final UserService userService;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("activeUsers", userService.findActive());
        model.addAttribute("deletedUsers", userService.findDeleted());
        model.addAttribute("menuElements", getDefaultMenu());
        model.addAttribute("slideMenuElements", getDefaultSlideMenu());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("menuElements", getDefaultMenu());
        model.addAttribute("slideMenuElements", getDefaultSlideMenu());
        return "userEdit";
    }

    @PostMapping
    public String userEditedSave(
            @RequestParam String username,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam Map <String, String> form,
            @RequestParam("userId") User user) {
        userService.adaptEditedUserAndSave(username, firstName, lastName, form, user);
        return "redirect:/user";
    }

    @PostMapping("/delete")
    public String delete(User user) {
        userService.delete(user);
        return "redirect:/user";
    }

    @PostMapping("/renew")
    public String renew(User user) {
        userService.reactivate(user);
        return "redirect:/user";
    }

}
