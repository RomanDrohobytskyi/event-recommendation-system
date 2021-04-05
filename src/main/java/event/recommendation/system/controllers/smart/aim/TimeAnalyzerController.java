package event.recommendation.system.controllers.smart.aim;

import event.recommendation.system.entities.aim.Aim;
import event.recommendation.system.entities.time.data.Time;
import event.recommendation.system.menu.MenuTabs;
import event.recommendation.system.repositories.AimRepository;
import event.recommendation.system.services.time.SmartAimTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/analyzer")
@PreAuthorize("hasAuthority('USER')")
@RequiredArgsConstructor
public class TimeAnalyzerController {

    private final SmartAimTimeService timeService;
    private final AimRepository aimRepository;

    @GetMapping
    @RequestMapping("/{aim}")
    public String getTimeAnalyzer(@PathVariable Aim aim, Model model) {
        List<Time> lastSevenDaysTime = timeService.getLastWeekTime(aim.getId());
        model.addAttribute("aim", aim);
        model.addAttribute("loggedTime", aim.getLoggedTime());
        model.addAttribute("mostProductive", timeService.getMostActiveTime(aim.getLoggedTime()));
        model.addAttribute("lessProductive", timeService.getLessActiveTime(aim.getLoggedTime()));
        model.addAttribute("lastSevenDaysTime", lastSevenDaysTime);
        model.addAttribute("loggedTimeSum", timeService.getAimLoggedTimeSum(aim.getLoggedTime()));
        model.addAttribute("menuElements", MenuTabs.timeAnalyzerMenu());
        model.addAttribute("slideMenuElements", MenuTabs.defaultSlideMenu());
        return "time_analyzer";
    }

    @GetMapping("/{aim}/delete/{time}")
    public String deleteAimTime(
            @PathVariable Time time,
            @PathVariable Aim aim,
            Map<String, Object> model) {

        timeService.deleteTime(time);
        Aim reloadedAim = (aimRepository.findById(aim.getId())).orElseThrow(IllegalArgumentException::new);
        model.put("aim", reloadedAim);
        model.put("logged_time", reloadedAim.getLoggedTime());

        return "redirect:/analyzer/" + aim.getId()+ "#timeTable";
    }
}
