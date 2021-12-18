package event.recommendation.system.menu;

public class CreatedMenuElements {

    public final static MenuElement homePage = new MenuElement("homePage", "/", "w3-bar-item w3-button","fa fa-home", "HOME", "Home page");
    public final static MenuElement login = new MenuElement("login", "/login", "w3-bar-item w3-button w3-hide-small w3-right w3-hover-red","fas fa-sign-in-alt", "", "Login");
    public final static MenuElement profile = new MenuElement("profile", "/userProfile", "w3-bar-item w3-button w3-hide-small w3-right w3-hover-red user-name","fa fa-user", "", "Profile");

    public final static MenuElement upToTheTop = new MenuElement("upToTheTop", "#home", "w3-bar-item w3-button","fa fa-chevron-up", "UP", "Up to the top");
    public final static MenuElement about = new MenuElement("about", "#about", "w3-bar-item w3-button w3-hide-small","fa fa-user", "ABOUT", "About me");

    public final static MenuElement events = new MenuElement("events", "/events", "w3-bar-item w3-button", "fa fa-calendar", "EVENTS", "EVENTS");
    public final static MenuElement eventsCreation = new MenuElement("eventsCreation", "/events/creation", "w3-bar-item w3-button", "fa fa-calendar", "EVENTS CREATION", "EVENTS CREATION");
    public final static MenuElement userEvents = new MenuElement("userEvents", "/events/user", "w3-bar-item w3-button", "fa fa-calendar", "USER EVENTS", "USER EVENTS");
    public final static MenuElement userAnalyzer = new MenuElement("userAnalyzer", "/userAnalyzer/activity", "w3-bar-item w3-button", "fa fa-circle-o", "USER ANALYZER", "USER ANALYZER");
    public final static MenuElement users = new MenuElement("users", "/user", "w3-bar-item w3-button", "fa fa-user-circle", "USERS", "USERS");

}
