package event.recommendation.system.controllers.smart.aim;

import event.recommendation.system.entities.aim.Aim;
import event.recommendation.system.menu.MenuTabs;
import event.recommendation.system.services.aim.SmartAimService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/editAim")
@PreAuthorize("hasAuthority('USER')")
@RequiredArgsConstructor
public class EditAimController {

    private final SmartAimService aimService;

    @GetMapping("{aim}")
    public String getEditForm(@PathVariable Aim aim, Model model) {
        model.addAttribute("aim", aim);
        model.addAttribute("menuElements", MenuTabs.defaultMenu());
        model.addAttribute("slideMenuElements", MenuTabs.defaultSlideMenu());
        return "editAim";
    }

    @PostMapping("/saveEditedAim")
    public String saveEditedMessage(
            @RequestParam String title,
            @RequestParam String text,
            @RequestParam String description,
            @RequestParam String specific,
            @RequestParam String measurable,
            @RequestParam String attainable,
            @RequestParam String relevant,
            @RequestParam String timeBased,
            @RequestParam("aimId") Aim aim) {

        aim = aimService.editAndSave(title, text, description, specific, measurable, attainable, relevant, timeBased, aim);
        return "redirect:/main_aim#aim_" + aim.getId();
    }

    @GetMapping("/cancel")
    public String cancel(){
        return  "redirect:/main_aim#aimsTable";
    }

}
