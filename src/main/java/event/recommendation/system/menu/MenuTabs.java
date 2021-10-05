package event.recommendation.system.menu;

import event.recommendation.system.entities.User;
import event.recommendation.system.managers.UserManager;

import java.util.ArrayList;
import java.util.List;

import static event.recommendation.system.menu.CreatedMenuElements.*;
import static java.util.Objects.*;
import static org.apache.commons.collections.CollectionUtils.isEmpty;

public class MenuTabs {
    private final UserManager userManager;
    private static MenuTabs INSTANCE;
    private List<MenuElement> defaultMenu;
    private List<MenuElement> defaultSlideMenu;

    private MenuTabs() {
        this.userManager = new UserManager();
    }

    public static MenuTabs getInstance() {
        return requireNonNullElseGet(INSTANCE, () ->  INSTANCE = new MenuTabs());
    }

    public List<MenuElement> getDefaultMenu() {
        if(isEmpty(defaultMenu)) {
            defaultMenu = createDefaultMenuItems();
        }
        addOrRemoveUserNames();
        return defaultMenu;
    }

    public List<MenuElement> getDefaultSlideMenu() {
        if(isEmpty(defaultSlideMenu)) {
            defaultSlideMenu = createDefaultSlideMenuItems();
        }
        return defaultSlideMenu;
    }

    private List<MenuElement> createDefaultMenuItems() {
        List<MenuElement> menuElements = new ArrayList<>();
        menuElements.add(homePage);
        menuElements.add(upToTheTop);
        menuElements.add(about);
        menuElements.add(login);
        menuElements.add(profile);
        return menuElements;
    }

    private List<MenuElement> createDefaultSlideMenuItems() {
        List<MenuElement> menuElements = new ArrayList<>();
        menuElements.add(events);
        menuElements.add(userEvents);
        menuElements.add(eventsCreation);
        menuElements.add(userAnalyzer);
        if (userManager.isLoggedUserAdmin()){
            menuElements.add(users);
        }
        return menuElements;
    }

    private void addOrRemoveUserNames() {
        User user = userManager.getLoggedInUser();
        if (shouldAddUserName(user)){
            addUserNamesMenuElement(user);
        } else if (isNull(user)){
            removeNamesMenuElement();
        }
    }

    private boolean shouldAddUserName(User user) {
        return nonNull(user) && defaultMenu.stream()
                .noneMatch(menuElement -> menuElement.getDescription().equals(user.getUsername()));
    }

    private void removeNamesMenuElement() {
        defaultMenu.stream()
                .filter(menuElement -> menuElement.getCssClass().contains("user-name"))
                .findFirst()
                .ifPresent(menuElement -> menuElement.setDescription(""));
    }

    private void addUserNamesMenuElement(User user) {
        profile.setDescription(user.getUsername());
    }

}
