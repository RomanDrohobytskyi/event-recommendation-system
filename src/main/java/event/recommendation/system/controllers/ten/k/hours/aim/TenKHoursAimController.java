package event.recommendation.system.controllers.ten.k.hours.aim;

import event.recommendation.system.entities.aim.TenThousandHoursAim;
import event.recommendation.system.entities.user.User;
import event.recommendation.system.menu.MenuTabs;
import event.recommendation.system.services.aim.TenThousandHoursAimService;
import event.recommendation.system.services.time.TenThousandHoursAimTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TenKHoursAimController {

    private final TenThousandHoursAimService aimService;
    private final TenThousandHoursAimTimeService aimTimeService;

    @GetMapping("/ten_thousand_hours_aim")
    public String allAims(Model model){
        model.addAttribute("all_aims", aimService.getAllLoggedUserAims());
        model.addAttribute("timeSum", aimTimeService.getAimsLoggedTimeSum((aimService.getAllLoggedUserAims())));
        model.addAttribute("allAimsTimeSum", aimTimeService.getAimsLoggedTimeSum((aimService.getAllLoggedUserAims())));
        model.addAttribute("menuElements", MenuTabs.smartGoalsMainMenu());
        model.addAttribute("slideMenuElements", MenuTabs.defaultSlideMenu());
        return "ten_thousand_hours_aim";
    }

    @GetMapping("/ten_thousand_hours_aim/add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String text,
            Map<String, Object> model) {

        aimService.createAim(title, description, text, user);
        model.put("aims",  user.getTenThousandHoursAims());
        return "redirect:/ten_thousand_hours_aim#aimsTable";
    }

    @GetMapping("/ten_thousand_hours_aim/delete/{aim}")
    public String delete(
            @PathVariable TenThousandHoursAim aim,
            Map<String, Object> model) {

        aimService.delete(aim);
        model.put("aims",  aimService.getAllLoggedUserAims());
        return "redirect:/ten_thousand_hours_aim#aimsTable";
    }

    @GetMapping("/ten_thousand_hours_aim/achieve/{aim}")
    public String achieve(@PathVariable TenThousandHoursAim aim, Map<String, Object> model){
        aimService.achieve(aim);
        return "redirect:/ten_thousand_hours_aim#aimsTable";
    }
}
