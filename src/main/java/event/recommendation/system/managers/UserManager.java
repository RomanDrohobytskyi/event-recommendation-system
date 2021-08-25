package event.recommendation.system.managers;

import event.recommendation.system.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.nonNull;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

public class UserManager {

    @Transactional
    public User getLoggedInUser(){
        Authentication authentication = getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")){
            return null;
        }
        return (User) authentication.getPrincipal();
    }

    public boolean isLoggedUserAdmin(){
        User loggedInUser = getLoggedInUser();
        return nonNull(loggedInUser) && loggedInUser.isAdmin();
    }
}
