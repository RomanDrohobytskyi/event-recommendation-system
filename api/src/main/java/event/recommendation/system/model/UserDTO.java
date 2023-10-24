package event.recommendation.system.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String avatar;
    private String activationCode;
    private boolean active = false;
    private boolean enabled = false;
    private boolean firstLogin = true;
/*  TODO
    private Set<Role> roles;
    private Set<Event> createdEvents;
    private Set<Event> events;
    private Set<Tag> tags;
    private Set<EventRating> rates;
    private Set<Subscription> subscriptions;
    private Set<Notification> notifications;*/

}
