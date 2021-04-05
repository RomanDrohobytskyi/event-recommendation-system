package event.recommendation.system.services.authorisation;

import event.recommendation.system.entities.token.RegistrationVerificationToken;
import event.recommendation.system.entities.user.User;
import event.recommendation.system.exceptions.ActivationCodeNotFoundException;
import event.recommendation.system.exceptions.RegistrationVerificationTokenExpiredException;
import event.recommendation.system.repositories.RegistrationVerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationVerificationTokenService {
    private static final int EXPIRATION = 60 * 24;
    private final RegistrationVerificationTokenRepository verificationTokenRepository;

    public RegistrationVerificationToken createAndSaveVerificationToken(User user) {
        RegistrationVerificationToken token = createVerificationToken(user);
        return save(token);
    }

    private RegistrationVerificationToken createVerificationToken(User user) {
        return  RegistrationVerificationToken.builder()
                .user(user)
                .token(user.getActivationCode())
                .expiryDate(calculateExpiryDate())
                .build();
    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, EXPIRATION);
        return new Date(cal.getTime().getTime());
    }

    private RegistrationVerificationToken save(RegistrationVerificationToken token) {
        return verificationTokenRepository.save(token);
    }

    public boolean verifyRegistrationToken(String code) {
        return existAndNotExpired(code);
    }

    private boolean existAndNotExpired(String code) {
        RegistrationVerificationToken verificationToken = getByToken(code)
                .orElseThrow(() -> new ActivationCodeNotFoundException(getCodeNotFoundExceptionMessage(code)));
        if (isExpired(verificationToken)) {
            throw new RegistrationVerificationTokenExpiredException(getExpiredTokenExceptionMessage(code));
        }
        return true;
    }

    private boolean isExpired(RegistrationVerificationToken registrationVerificationToken) {
        long tokenExpireTime = registrationVerificationToken.getExpiryDate().getTime();
        long currentTime =  Calendar.getInstance().getTime().getTime();
        return (tokenExpireTime - currentTime) <= 0;
    }

    private String getCodeNotFoundExceptionMessage(String code) {
        return "Sorry, it seems like activation code: " + code + " does not exist!";
    }

    private String getExpiredTokenExceptionMessage(String code) {
        return "Sorry, Your activation code: " + code + " has been expired!";
    }

    private Optional<RegistrationVerificationToken> getByToken(String token) {
        return verificationTokenRepository.getByToken(token);
    }
}
