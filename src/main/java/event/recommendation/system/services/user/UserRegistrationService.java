package event.recommendation.system.services.user;

import event.recommendation.system.entities.RegistrationVerificationToken;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.UserRegisterValidationState;
import event.recommendation.system.exceptions.ActivationCodeNotFoundException;
import event.recommendation.system.exceptions.RegistrationVerificationTokenExpiredException;
import event.recommendation.system.services.MailSenderService;
import event.recommendation.system.services.authorisation.RegistrationValidationService;
import event.recommendation.system.services.authorisation.RegistrationVerificationTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.UUID;

import static event.recommendation.system.enums.UserRegisterValidationState.CODE_SENDING_FAILED;
import static event.recommendation.system.enums.UserRegisterValidationState.SUCCESS;
import static event.recommendation.system.menu.MenuTabs.getLoginMenu;
import static event.recommendation.system.roles.Role.USER;

//TODO: RegistrationTokenExpired
@Service
@Slf4j
@RequiredArgsConstructor
public class UserRegistrationService {
    private final UserService userService;
    private final RegistrationVerificationTokenService verificationTokenService;
    private final MailSenderService mailSenderService;
    private final RegistrationValidationService registrationValidationService;

    public RedirectView addUserAndRedirect(User user, String passwordConfirm, RedirectAttributes attributes) {
        attributes.addAttribute("menuElements", getLoginMenu());
        UserRegisterValidationState validationResult = addUserIfValid(user, passwordConfirm, attributes);
        return validationResult.equals(SUCCESS) ? successRedirect(user, attributes) : errorRedirect(user, attributes);
    }

    private UserRegisterValidationState addUserIfValid(User user, String passwordConfirm, RedirectAttributes attributes) {
        UserRegisterValidationState validationResult = registrationValidationService.validateUser(user, passwordConfirm);
        if(validationResult.equals(SUCCESS)) {
            setNewUserData(user);
            UserRegisterValidationState messageSendingResult = sendActivationCodeAndSaveUser(user);
            attributes.addFlashAttribute(messageSendingResult.getState(), messageSendingResult.getDescription());
            return messageSendingResult;
        }
        attributes.addFlashAttribute(validationResult.getState(), validationResult.getDescription());
        return validationResult;
    }

    private void setNewUserData(User user){
        user.setRoles(Collections.singleton(USER));
        user.setActivationCode(generateVerificationToken());
        userService.encodeUserPassword(user);
    }

    private RedirectView successRedirect(User user, RedirectAttributes attributes) {
        attributes.addFlashAttribute("message", "User "
                + user.getEmail()
                + " successfully registered!"
                + " Activate account by clicking on link in email message!");
        return new RedirectView("/login");
    }

    private RedirectView errorRedirect(User user, RedirectAttributes attributes) {
        attributes.addFlashAttribute("user", user);
        return new RedirectView("/registration");
    }

    private UserRegisterValidationState sendActivationCodeAndSaveUser(User user){
        try {
            sendActivationCode(user);
            userService.save(user);
            createAndSaveVerificationToken(user);
            return SUCCESS;
        } catch (Exception e) {
            log.error("Activation code sending failed, user email: " + user.getEmail(), e);
            return CODE_SENDING_FAILED;
        }
    }

    public void sendActivationCode(User user){
        String message = getRegistrationMessage(user);
        mailSenderService.send(user.getEmail(), "Events Recommendation System. Activation code", message);
    }

    private String getRegistrationMessage(User user) {
        return String.format(
                "Hello, %s!\n" + "Welcome to the Events recommendation application!\n"
                        + "To activate your account, please, click on a link below.\n"
                        + "http://localhost:8080/activate/%s\n"
                        + "Thank You \n%s,\n"
                        + "Best regards.",
                user.getFirstName() + " " + user.getLastName(),
                user.getActivationCode(),
                user.getFirstName() + " " + user.getLastName()
        );
    }

    private String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }

    public void activateUserByActivationCode(String code, Model model) {
        try{
            activateUserByActivationCode(code);
            model.addAttribute("message", "User successfully activated.");
        } catch (ActivationCodeNotFoundException | RegistrationVerificationTokenExpiredException e){
            model.addAttribute("message", e.getMessage());
            log.error(e.getMessage());
        }
    }

    private void activateUserByActivationCode(String code) {
        if (verificationTokenService.verifyRegistrationToken(code)) {
            User user = userService.findByActivationCode(code);
            activateUser(user);
            userService.save(user);
        } else {
            throw new RegistrationVerificationTokenExpiredException("Verification Token Expired");
        }
    }

    private void activateUser(User user) {
        user.setEnabled(true);
        user.setActive(true);
        user.setActivationCode(null);
    }

    private RegistrationVerificationToken createAndSaveVerificationToken(User user) {
        return verificationTokenService.createAndSaveVerificationToken(user);
    }

    public void resendVerificationToken(String email, Model model) {
        try {
            resendVerificationToken(email);
            model.addAttribute("message", "Activation code successfully resend!");
        } catch (Exception e){
            model.addAttribute("message", "Activation code was not resent!");
            log.error("Verification code resending failed: " + email, e);
        }
    }

    private void resendVerificationToken(String email) {
        User user = userService.findUserByEmail(email);
        generateNewVerificationTokenForUser(user);
        sendActivationCode(user);
    }

    private void generateNewVerificationTokenForUser(User user) {
        user.setActivationCode(generateVerificationToken());
    }
}
