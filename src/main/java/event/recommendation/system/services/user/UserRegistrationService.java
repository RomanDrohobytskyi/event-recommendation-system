package event.recommendation.system.services.user;

import event.recommendation.system.entities.token.RegistrationVerificationToken;
import event.recommendation.system.entities.user.User;
import event.recommendation.system.exceptions.ActivationCodeNotFoundException;
import event.recommendation.system.exceptions.RegistrationVerificationTokenExpiredException;
import event.recommendation.system.roles.Role;
import event.recommendation.system.services.MailSenderService;
import event.recommendation.system.services.authorisation.RegistrationVerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static event.recommendation.system.enums.UserRegisterValidationState.*;
import static event.recommendation.system.logger.LoggerJ.logError;

//TODO: refactor service - UserRegistrationService
//TODO: RegistrationTokenExpired
//TODO: Create all validation errors
//TODO: Redirect with message, that User registered an activate by clicking on link in email message
@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserService userService;
    private final RegistrationVerificationTokenService verificationTokenService;
    private final MailSenderService mailSenderService;

    public String addUserAndRedirect(User user, String passwordConfirm, Model model) {
        Map<String, Object> validationResult = validateAndAddUser(user, passwordConfirm, model);
        return validationResult.containsKey(SUCCESS.state)
                ? "redirect:/login" : "registration";
    }

    private Map<String, Object> validateAndAddUser(User user, String passwordConfirm, Model model) {
        Map<String, Object> validationResult = validateUser(user, passwordConfirm);
        model.addAllAttributes(validationResult);
        return validationResult;
    }

        /*

        public RedirectView addUserAndRedirect(User user, String passwordConfirm, RedirectAttributes attributes) {
        Map<String, Object> validationResult = addUserIfValid(user, passwordConfirm, attributes);
        return shouldRedirect(validationResult) ? successRedirect(user, attributes) : errorRedirect(user, attributes);
    }

    private RedirectView successRedirect(User user, RedirectAttributes attributes) {
        attributes.addFlashAttribute("message", "User "
                + user.getEmail()
                + " successfully registered!"
                + " Activate account by clicking on link in email message!");
        return new RedirectView("/login");
    }

    private RedirectView errorRedirect(User user, RedirectAttributes attributes) {
        attributes.addFlashAttribute("message", "Validation error");
        return new RedirectView("/registration");
    }


    private boolean shouldRedirect(Map<String, Object> validationResult) {
        return validationResult.containsKey(SUCCESS.state);
    }

    private Map<String, Object> addUserIfValid(User user, String passwordConfirm, RedirectAttributes attributes) {
        Map<String, Object> validationResult = validateUser(user, passwordConfirm);
        attributes.addAllAttributes(validationResult);
        return validationResult;
    }
     */



    public Map<String, Object> validateUser(User user, String passwordConfirm) {
        Map<String, Object> emailAndPasswordValidationResult = validateUserEmailAndPasswords(user, passwordConfirm);
        if (emailAndPasswordValidationResult.isEmpty()) {
            setNewUserData(user);
            return sendActivationCodeAndSaveUser(user);
        } else {
            return emailAndPasswordValidationResult;
        }
    }

    private Map<String, Object> validateUserEmailAndPasswords(User user, String passwordConfirm){
        if (userService.isUserEmailEmpty(user)) {
            return Map.of(EMPTY_EMAIL.state, EMPTY_EMAIL.description);
        } else if (userService.isUserExist(user)) {
            return Map.of(USER_EXIST.state, USER_EXIST.description);
        } else if (!userService.isPasswordsMatch(user.getPassword(), passwordConfirm)) {
            return Map.of(PASSWORDS_NOT_MATCHING.state,
                    PASSWORDS_NOT_MATCHING.description);
        } else {
            return Collections.emptyMap();
        }
    }

    private void setNewUserData(User user){
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(generateVerificationToken());
        userService.encodeUserPassword(user);
    }

    //TODO: refactor
    private Map<String, Object> sendActivationCodeAndSaveUser(User user){
        if (sendActivationCode(user)) {
            userService.save(user);
            createAndSaveVerificationToken(user);
            return Map.of(SUCCESS.state, SUCCESS.description);
        } else {
            return Map.of(CODE_SENDING_FAILED.state, CODE_SENDING_FAILED.description);
        }
    }

    public boolean sendActivationCode(User user){
        try{
            String message = getRegistrationMessage(user);
            mailSenderService.send(user.getEmail(), "Events Recommendation System. Activation code", message);
            return true;
        }catch (Exception e){
            logError(getClass(), e.getMessage());
        }
        return false;
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
            logError(this.getClass(), e.getMessage());
        }
    }

    private void activateUserByActivationCode(String code) {
        if (verificationTokenService.verifyRegistrationToken(code)) {
            User user = userService.findByActivationCode(code);
            user.setEnabled(true);
            user.setActive(true);
            user.setActivationCode(null);
            userService.save(user);
        }
    }

    private RegistrationVerificationToken createAndSaveVerificationToken(User user) {
        return verificationTokenService.createAndSaveVerificationToken(user);
    }







    public void resendVerificationToken(String email, Model model) {
        boolean resend = resendVerificationToken(email);
        if (resend) {
            model.addAttribute("message", "Activation code successfully resend!");
        } else {
            model.addAttribute("message", "Activation code was not resend!");
        }
    }

    private boolean resendVerificationToken(String email) {
        User user = userService.findUserByEmail(email);
        generateNewVerificationTokenForUser(user);
        return sendActivationCode(user);
    }

    private void generateNewVerificationTokenForUser(User user) {
        user.setActivationCode(generateVerificationToken());
    }
}
