package event.recommendation.system.exceptions;

import lombok.Getter;

@Getter
public class ActivationCodeNotFoundException extends RuntimeException {
    private final String message;

    public ActivationCodeNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
