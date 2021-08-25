package event.recommendation.system.menu;

public class CreatedMenuElements {

    public final static MenuElement HOME_PAGE = new MenuElement("homePage", "/", "w3-bar-item w3-button","fa fa-home", "HOME", "Home page");
    public final static MenuElement LOGIN = new MenuElement("login", "/login", "w3-bar-item w3-button w3-hide-small w3-right w3-hover-red","fa fa-sign-in", "", "Login");
    public final static MenuElement PROFILE = new MenuElement("profile", "/userProfile", "w3-bar-item w3-button w3-hide-small w3-right w3-hover-red user-name","fa fa-user", "", "Profile");

    public final static MenuElement UP_TO_THE_TOP = new MenuElement("upToTheTop", "#home", "w3-bar-item w3-button","fa fa-chevron-up", "UP", "Up to the top");
    public final static MenuElement ABOUT = new MenuElement("about", "#about", "w3-bar-item w3-button w3-hide-small","fa fa-user", "ABOUT", "About me");

    public final static MenuElement EVENTS = new MenuElement("events", "/events", "w3-bar-item w3-button", "fa fa-calendar", "EVENTS", "EVENTS");
    public final static MenuElement EVENTS_CREATION = new MenuElement("eventsCreation", "/events/creation", "w3-bar-item w3-button", "fa fa-calendar", "EVENTS CREATION", "EVENTS CREATION");
    public final static MenuElement USER_EVENTS = new MenuElement("userEvents", "/events/user", "w3-bar-item w3-button", "fa fa-calendar", "USER EVENTS", "USER EVENTS");
    public final static MenuElement USER_ANALYZER = new MenuElement("userAnalyzer", "/userAnalyzer/activity", "w3-bar-item w3-button", "fa fa-circle-o", "USER ANALYZER", "USER ANALYZER");
    public final static MenuElement USERS = new MenuElement("users", "/user", "w3-bar-item w3-button", "fa fa-user-circle", "USERS", "USERS");

}
