package event.recommendation.system.controllers.authorisation.registration;


import event.recommendation.system.entities.User;
import event.recommendation.system.services.controllers.RegistrationControllerService;
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
    private final RegistrationControllerService registrationControllerService;

    @GetMapping("/registration")
    public String registration(Model model) {
        registrationControllerService.onRegistrationView(model);
        return "registration";
    }

    @PostMapping("/registration")
    public RedirectView registerNewUser(User user, RedirectAttributes attributes,
                                        @RequestParam String passwordConfirm) {
        return registrationControllerService.onNewUserRegistration(attributes, user, passwordConfirm);
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {
        registrationControllerService.onActivate(code, model);
        return "login";
    }

    @GetMapping("/registration/resendVerificationCode")
    public String resendVerificationCode(@RequestParam String email, Model model) {
        registrationControllerService.onResendVerificationCode(email, model);
        return "registration";
    }
}
