package event.recommendation.system.services.user;

import event.recommendation.system.entities.User;
import event.recommendation.system.enums.UserRegisterValidationState;
import event.recommendation.system.exceptions.ActivationCodeNotFoundException;
import event.recommendation.system.repositories.UserRepository;
import event.recommendation.system.roles.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static event.recommendation.system.roles.Role.stringRoles;
import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByEmail(username);
    }

    public User findUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public Iterable<User> findActive(){
        return userRepository.findAllByActive(true);
    }

    public Iterable<User> findDeleted(){
        return userRepository.findAllByActive(false);
    }

    public boolean isUserExist(User user){
        return userRepository.findUserByEmail(user.getEmail()).isPresent();
    }

    public boolean isUserEmailEmpty(User user){
        return isEmpty(user.getEmail());
    }

    public boolean isPasswordsMatch(String password, String confirmedPassword){
        return password.equals(confirmedPassword);
    }

    public UserRegisterValidationState isPasswordStrongEnough(String password) {
        if(password.length() >= 8) {
            Pattern letter = Pattern.compile("[a-zA-z]");
            Matcher hasLetter = letter.matcher(password);
            if(!hasLetter.find()) {
                return UserRegisterValidationState.PASSWORDS_HAS_NO_LETTER;
            }

            Pattern digit = Pattern.compile("[0-9]");
            Matcher hasDigit = digit.matcher(password);
            if (!hasDigit.find()) {
                return UserRegisterValidationState.PASSWORDS_HAS_NO_DIGIT;
            }

            Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
            Matcher hasSpecial = special.matcher(password);
            if(!hasSpecial.find()) {
                return UserRegisterValidationState.PASSWORDS_HAS_NO_SPECIAL;
            }
            return UserRegisterValidationState.SUCCESS;
        }
       return UserRegisterValidationState.PASSWORDS_NOT_LONG_ENOUGH;
    }

    public boolean isFirstLogin(User loggedInUser) {
        return nonNull(loggedInUser) && loggedInUser.isFirstLogin();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void adaptEditedUserAndSave(String username, String firstName, String lastName, Map<String, String> form, User user) {
        adaptUserRoles(form, user);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.save(user);
    }

    private void adaptUserRoles(Map<String, String> form, User user) {
        user.getRoles().clear();

        stringRoles().stream()
                .filter(form::containsKey)
                .forEach(role -> user.getRoles().add(Role.getAuthority(role)));
    }

    public User findByActivationCode(String code) {
        return userRepository.findByActivationCode(code)
                .orElseThrow(() -> new ActivationCodeNotFoundException("Activation code: " + code + " not found"));
    }

    public void encodeUserPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    public void delete(User user) {
        user.setActive(false);
        user.setEnabled(false);
        userRepository.save(user);
    }

    public void reactivate(User user) {
        user.setActive(true);
        user.setEnabled(true);
        userRepository.save(user);
    }
}
