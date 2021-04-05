package event.recommendation.system.exceptions;

import lombok.Getter;

@Getter
public class NoLoggedInUser extends RuntimeException {
    private final String message;

    public NoLoggedInUser(String message) {
        super(message);
        this.message = message;
    }
}
