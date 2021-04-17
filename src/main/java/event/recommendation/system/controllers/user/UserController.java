package event.recommendation.system.controllers.user;

import event.recommendation.system.entities.user.User;
import event.recommendation.system.menu.MenuTabs;
import event.recommendation.system.repositories.UserRepository;
import event.recommendation.system.roles.Role;
import event.recommendation.system.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository iUserRepository;
    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model){
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("menuElements", MenuTabs.getInstance().getDefaultMenu());
        model.addAttribute("slideMenuElements", MenuTabs.getInstance().getDefaultSlideMenu());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("menuElements", MenuTabs.getInstance().getDefaultMenu());
        model.addAttribute("slideMenuElements", MenuTabs.getInstance().getDefaultSlideMenu());
        return "userEdit";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEditedSave(
            @RequestParam String username,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam Map <String, String> form,
            @RequestParam("userId") User user) {

        userService.adaptEditedUserAndSave(username, firstName, lastName, form, user);
        return "redirect:/user";
    }

    @GetMapping("/delete/{user}")
    public String delete(@PathVariable User user,  Map<String, Object> model){
        model.put("users", iUserRepository.findAll());
        return "redirect:/user";
    }

}
