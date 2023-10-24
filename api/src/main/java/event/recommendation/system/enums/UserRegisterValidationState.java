package event.recommendation.system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRegisterValidationState {
    EMPTY_EMAIL("EMPTY_EMAIL", "Sorry, Your email is empty, please check it again"),
    USER_EXIST("USER_EXIST", "Sorry, user with this email already exist!"),
    PASSWORDS_NOT_MATCHING("PASSWORDS_NOT_MATCHING", "Sorry, but Your passwords do not match, check it again!"),
    PASSWORDS_NOT_LONG_ENOUGH("PASSWORDS_NOT_LONG_ENOUGH", "Sorry, but Your password is not long enough, should contains at least 8 characters!"),
    PASSWORDS_HAS_NO_LETTER("PASSWORDS_HAS_NO_LETTER", "Sorry, but Your password has no upper-case letter!"),
    PASSWORDS_HAS_NO_DIGIT("PASSWORDS_HAS_NO_DIGIT", "Sorry, but Your password has no digit!"),
    PASSWORDS_HAS_NO_SPECIAL("PASSWORDS_HAS_NO_SPECIAL", "Sorry, but Your password has no special character!"),
    CODE_SENDING_FAILED("CODE_SENDING_FAILED", "We can`t send to You activation code, sorry!"),
    SUCCESS("SUCCESS", "Success");

    public final String state;
    public final String description;
}
