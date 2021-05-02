package event.recommendation.system.controllers.authorisation.registration;


import event.recommendation.system.entities.user.User;
import event.recommendation.system.services.user.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserRegistrationService userRegistrationService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    //todo refactor
    @PostMapping("/registration")
    public RedirectView addUser(User user, RedirectAttributes attributes,
                                @RequestParam String passwordConfirm) {
        return userRegistrationService.addUserAndRedirect(user, passwordConfirm, attributes);
    }

    //TODO: message in login page about activation
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        userRegistrationService.activateUserByActivationCode(code, model);
        return "login";
    }

    @GetMapping("/registration/resendVerificationCode")
    public String resendVerificationCode(@RequestParam String email, Model model) {
        userRegistrationService.resendVerificationToken(email, model);
        return "registration";
    }
}
