package event.recommendation.system.model;

import lombok.*;
import main.java.event.recommendation.system.common.BaseEntity;
import main.java.event.recommendation.system.notifications.entity.Notification;
import main.java.event.recommendation.system.roles.Role;
import main.java.event.recommendation.system.subscription.entity.Subscription;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity (name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User extends BaseEntity implements UserDetails {
    @Email
    @NotNull
    private String email;
    @NotNull
    @Size(min = 1, max = 24)
    private String username;
    @NotNull
    @Size(min = 1, max = 34)
    private String firstName;
    @NotNull
    @Size(min = 1, max = 42)
    private String lastName;
    @NotNull
    @ToString.Exclude
    private String password;
    private String avatar;
    private String activationCode;
    @Builder.Default
    @Column(columnDefinition = "boolean default false")
    private boolean active = false;
    @Builder.Default
    @Column(columnDefinition = "boolean default false")
    private boolean enabled = false;
    @NotNull
    @Builder.Default
    @Column(columnDefinition = "boolean default true")
    private boolean firstLogin = true;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Set<Role> roles;
    @OneToMany(mappedBy = "creator", fetch = FetchType.EAGER)
    private Set<Event> createdEvents;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "participants_id"),
            inverseJoinColumns = @JoinColumn(name = "events_id"))
    private Set<Event> events;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_tags",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<TagDTO> tags;


    /*TODO: REMOVE, move get to service*/
    @OneToMany(mappedBy = "evaluator", fetch = FetchType.EAGER)
    private Set<EventRating> rates;

    /*TODO: REMOVE, move get to service*/
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "subscription_id")}
    )
    private Set<Subscription> subscriptions;

    /*TODO: REMOVE, move get to service*/
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_notifications",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "notification_id")}
    )
    private Set<Notification> notifications;

    public boolean isAdmin(){
        return getRoles().contains(Role.ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String getNames(){
        return firstName + " " + lastName;
    }
}
