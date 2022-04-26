package event.recommendation.system.services.user;

import event.recommendation.system.entities.User;
import event.recommendation.system.enums.SubscriptionType;
import event.recommendation.system.managers.UserManager;
import event.recommendation.system.repositories.UserRepository;
import event.recommendation.system.services.FileService;
import event.recommendation.system.subscription.service.SubscriptionService;
import event.recommendation.system.subscription.subscriber.observable.NotificationSubscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static event.recommendation.system.enums.SubscriptionType.asStream;
import static event.recommendation.system.enums.SubscriptionType.getAvailableSubscriptionsDescriptions;
import static event.recommendation.system.menu.MenuTabs.getDefaultMenu;
import static event.recommendation.system.menu.MenuTabs.getDefaultSlideMenu;
import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserRepository userRepository;
    private final FileService fileService;
    private final SubscriptionService subscriptionService;
    private final NotificationSubscription notificationSubscription;
    private final UserManager userManager = new UserManager();

    public void adaptAndSaveEditedUserProfile(MultipartFile avatar, String firstName,
                                              String lastName, String username, User user) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        addUserAvatar(user, avatar);
        userRepository.save(user);
    }

    private void addUserAvatar(User user, MultipartFile avatar) {
        user.setAvatar(fileService.uploadFile(avatar));
    }

    public void initModel(Model model) {
        model.addAttribute("user", userManager.getLoggedInUser());
        model.addAttribute("menuElements", getDefaultMenu());
        model.addAttribute("slideMenuElements", getDefaultSlideMenu());
        model.addAttribute("subscriptions", getAvailableSubscriptionsDescriptions());
        initSubscriptions(model);
    }

    private void initSubscriptions(Model model) {
        User loggedUser = userManager.getLoggedInUser();
        List<String> userSubscriptions = loggedUser.getSubscriptions().stream()
                .map(subscription -> subscription.getType().getType())
                .collect(toList());
        model.addAttribute("userSubscriptions", userSubscriptions);
    }

    public void subscribe(User user, Map<String, String> form) {
        Set<SubscriptionType> subscriptionTypes = getSubscriptionsToSubscribe(form);
        notificationSubscription.subscribe(user, subscriptionTypes);
    }

    private Set<SubscriptionType> getSubscriptionsToSubscribe(Map<String, String> form) {
        return asStream()
                .filter(subscription -> form.containsKey(valueOf(subscription.getType())))
                .collect(Collectors.toSet());
    }
}
