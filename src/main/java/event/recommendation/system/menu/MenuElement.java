package event.recommendation.system.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MenuElement {
    private String id;
    private String url;
    private String cssClass;
    private String hrefCssClass;
    private String name;
    private String description;
}
