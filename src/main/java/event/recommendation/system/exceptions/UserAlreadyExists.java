package event.recommendation.system.exceptions;

import lombok.Getter;

@Getter
public class UserAlreadyExists extends RuntimeException {
    private final String message;

    public UserAlreadyExists(String message) {
        super(message);
        this.message = message;
    }
}