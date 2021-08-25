package event.recommendation.system.menu;

import event.recommendation.system.entities.User;
import event.recommendation.system.managers.UserManager;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static event.recommendation.system.menu.CreatedMenuElements.*;
import static java.util.Objects.nonNull;

public class MenuTabs {
    private final UserManager userManager;
    private static MenuTabs INSTANCE;
    private List<MenuElement> defaultMenu;
    private List<MenuElement> defaultSlideMenu;

    private MenuTabs(){
        this.userManager = new UserManager();
    }

    public static MenuTabs getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MenuTabs();
        }
        return INSTANCE;
    }

    public List<MenuElement> getDefaultMenu() {
        if(CollectionUtils.isEmpty(defaultMenu)) {
            defaultMenu = createDefaultMenuItems();
        }
        addOrRemoveUserNames();
        return defaultMenu;
    }

    public List<MenuElement> getDefaultSlideMenu() {
        if(CollectionUtils.isEmpty(defaultSlideMenu)) {
            defaultSlideMenu = createDefaultSlideMenuItems();
        }
        return defaultSlideMenu;
    }

    private List<MenuElement> createDefaultMenuItems() {
        List<MenuElement> menuElements = new ArrayList<>();
        menuElements.add(HOME_PAGE);
        menuElements.add(UP_TO_THE_TOP);
        menuElements.add(ABOUT);
        menuElements.add(LOGIN);
        menuElements.add(PROFILE);
        return menuElements;
    }

    private List<MenuElement> createDefaultSlideMenuItems() {
        List<MenuElement> menuElements = new ArrayList<>();
        menuElements.add(EVENTS);
        menuElements.add(USER_EVENTS);
        menuElements.add(EVENTS_CREATION);
        menuElements.add(USER_ANALYZER);
        if (userManager.isLoggedUserAdmin()){
            menuElements.add(USERS);
        }
        return menuElements;
    }

    private void addOrRemoveUserNames() {
        User user = userManager.getLoggedInUser();
        if (shouldAddUserName(user)){
            addUserNamesMenuElement(user);
        } else if (user == null){
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
        PROFILE.setDescription(user.getUsername());
    }

}
