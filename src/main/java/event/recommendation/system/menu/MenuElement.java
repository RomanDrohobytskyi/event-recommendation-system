package event.recommendation.system.menu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuElement {
    private String id;
    private String url;
    private String cssClass;
    private String hrefCssClass;
    private String name;
    private String description;

    public MenuElement(String id, String url, String hrefCssClass, String cssClass, String name, String description) {
        this.id = id;
        this.url = url;
        this.hrefCssClass = hrefCssClass;
        this.cssClass = cssClass;
        this.name = name;
        this.description = description;
    }
}
