package event.recommendation.system.services.user;

import event.recommendation.system.entities.User;
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
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

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

    public boolean isUserExist(User user){
        return userRepository.findUserByEmail(user.getEmail()).isPresent();
    }

    public boolean isUserEmailEmpty(User user){
        return StringUtils.isEmpty(user.getEmail());
    }

    public boolean isPasswordsMatch(String password, String confirmedPassword){
        return password.equals(confirmedPassword);
    }

    public boolean isFirstLogin(User loggedInUser) {
        return loggedInUser != null && loggedInUser.isFirstLogin();
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
        Set<String> allRoles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(toSet());
        allRoles.stream()
                .filter(form::containsKey)
                .forEach(role -> user.getRoles().add(Role.valueOf(form.get(role))));
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
}
