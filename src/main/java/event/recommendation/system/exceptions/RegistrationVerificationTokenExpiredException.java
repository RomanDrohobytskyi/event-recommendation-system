package event.recommendation.system.exceptions;

import lombok.Getter;

@Getter
public class RegistrationVerificationTokenExpiredException extends RuntimeException {
    private final String message;

    public RegistrationVerificationTokenExpiredException(String message) {
        super(message);
        this.message = message;
    }
}