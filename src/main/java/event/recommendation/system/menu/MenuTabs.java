package event.recommendation.system.menu;

import event.recommendation.system.entities.User;
import event.recommendation.system.managers.UserManager;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
        menuElements.add(CreatedMenuElements.homePage);
        menuElements.add(CreatedMenuElements.upToTheTop);
        menuElements.add(CreatedMenuElements.about);
        menuElements.add(CreatedMenuElements.login);
        menuElements.add(CreatedMenuElements.profile);
        return menuElements;
    }

    private List<MenuElement> createDefaultSlideMenuItems() {
        List<MenuElement> menuElements = new ArrayList<>();
        menuElements.add(CreatedMenuElements.events);
        menuElements.add(CreatedMenuElements.userEvents);
        menuElements.add(CreatedMenuElements.eventsCreation);
        menuElements.add(CreatedMenuElements.userAnalyzer);
        if (userManager.isLoggedUserAdmin()){
            menuElements.add(CreatedMenuElements.users);
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
        return user != null && defaultMenu.stream()
                .noneMatch(menuElement -> menuElement.getDescription().equals(user.getUsername()));
    }

    private void removeNamesMenuElement() {
        defaultMenu.stream()
                .filter(menuElement -> menuElement.getCssClass().contains("user-name"))
                .findFirst()
                .ifPresent(menuElement -> menuElement.setDescription(""));
    }

    private void addUserNamesMenuElement(User user) {
        MenuElement userName = CreatedMenuElements.profile;
        userName.setDescription(user.getUsername());
    }

}
