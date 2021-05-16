package event.recommendation.system.services.authorisation;

import event.recommendation.system.entities.User;
import event.recommendation.system.enums.UserRegisterValidationState;
import event.recommendation.system.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static event.recommendation.system.enums.UserRegisterValidationState.*;

@Service
@RequiredArgsConstructor
public class RegistrationValidationService {
    private final UserService userService;

    public UserRegisterValidationState validateUser(User user, String passwordConfirm) {
        return validateUserEmailAndPasswords(user, passwordConfirm);
    }

    private UserRegisterValidationState validateUserEmailAndPasswords(User user, String passwordConfirm) {
        if (userService.isUserEmailEmpty(user)) {
            return EMPTY_EMAIL;
        } else if (userService.isUserExist(user)) {
            return USER_EXIST;
        } else if (!userService.isPasswordsMatch(user.getPassword(), passwordConfirm)) {
            return PASSWORDS_NOT_MATCHING;
        } else {
            return SUCCESS;
        }
    }
}
