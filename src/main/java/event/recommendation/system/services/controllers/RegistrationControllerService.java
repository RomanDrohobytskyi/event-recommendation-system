package event.recommendation.system.services.controllers;

import event.recommendation.system.entities.User;
import event.recommendation.system.services.builders.ModelBuilder;
import event.recommendation.system.services.user.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Service
@RequiredArgsConstructor
public class RegistrationControllerService {
    private final ModelBuilder modelBuilder;
    private final UserRegistrationService userRegistrationService;

    public void onRegistrationView(Model model) {
        modelBuilder.builder(model).withLoginMenu();
    }

    public RedirectView onNewUserRegistration(RedirectAttributes model, User user, String passwordConfirm) {
        modelBuilder.builder(model).withDefaultMenu();
        return userRegistrationService.addUserAndRedirect(user, passwordConfirm, model);
    }

    public void onActivate(String code, Model model) {
        modelBuilder.builder(model).withLoginMenu();
        userRegistrationService.activateUserByActivationCode(code, model);
    }


    public void onResendVerificationCode(String email, Model model) {
        modelBuilder.builder(model).withLoginMenu();
        userRegistrationService.resendVerificationToken(email, model);
    }

}
