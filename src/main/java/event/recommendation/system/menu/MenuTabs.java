package event.recommendation.system.menu;

import event.recommendation.system.entities.User;
import event.recommendation.system.managers.UserManager;
import lombok.NoArgsConstructor;

import java.util.List;

import static event.recommendation.system.menu.CreatedMenuElements.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

@NoArgsConstructor(access = PRIVATE)
public class MenuTabs {
    private static final UserManager userManager = new UserManager();
    private static List<MenuElement> defaultMenu;
    private static List<MenuElement> loginMenu;
    private static List<MenuElement> defaultSlideMenu;

    public static List<MenuElement> getDefaultMenu() {
        if(isEmpty(defaultMenu)) {
            initializeDefaultMenu();
        }
        addOrRemoveUserNames();
        return defaultMenu;
    }

    public static List<MenuElement> getLoginMenu() {
        if(isEmpty(loginMenu)) {
            initializeLoginMenu();
        }
        return loginMenu;
    }

    public static List<MenuElement> getDefaultSlideMenu() {
        if(isEmpty(defaultSlideMenu)) {
            initializeDefaultSlideMenuItems();
        }
        return defaultSlideMenu;
    }

    private static void initializeDefaultMenu() {
        defaultMenu = List.of(homePage, upToTheTop, about, login, profile);
    }

    private static void initializeLoginMenu() {
        loginMenu = List.of(homePage, upToTheTop, login);
    }

    private static void initializeDefaultSlideMenuItems() {
        defaultSlideMenu = List.of(events, userEvents, eventsCreation, userAnalyzer);
        if (userManager.isLoggedUserAdmin()){
            defaultSlideMenu.add(users);
        }
    }

    private static void addOrRemoveUserNames() {
        User user = userManager.getLoggedInUser();
        if (shouldAddUserName(user)){
            addUserNamesMenuElement(user);
        } else if (isNull(user)){
            removeNamesMenuElement();
        }
    }

    private static boolean shouldAddUserName(User user) {
        return nonNull(user) && defaultMenu.stream()
                .noneMatch(menuElement -> menuElement.getDescription().equals(user.getUsername()));
    }

    private static void removeNamesMenuElement() {
        defaultMenu.stream()
                .filter(menuElement -> menuElement.getCssClass().contains("user-name"))
                .findFirst()
                .ifPresent(menuElement -> menuElement.setDescription(""));
    }

    private static void addUserNamesMenuElement(User user) {
        profile.setDescription(user.getUsername());
    }

}
