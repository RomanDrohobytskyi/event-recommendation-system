package event.recommendation.system.entities;

import event.recommendation.system.roles.Role;
import lombok.*;
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
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private Set<Tag> tags;
    @OneToMany(mappedBy = "evaluator", fetch = FetchType.EAGER)
    private Set<EventRating> rates;

    public boolean isAdmin(){
        return getRoles().contains(Role.ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    //TODO
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //TODO
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //TODO
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String getNames(){
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "User{ " +
                "id = " + id +
                ", email = '" + email + '\'' +
                ", username = '" + username + '\'' +
                ", names = '" + getNames() + '\'' +
                '}';
    }


}
