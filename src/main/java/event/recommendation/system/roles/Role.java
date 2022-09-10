package event.recommendation.system.roles;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

public enum Role implements GrantedAuthority{
    USER ("USER"),
    ADMIN ("ADMIN");

    private String authority;

    Role(String authority){
        this.authority = authority;
    }

    @Override
    public String toString() {
        return this.authority;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    public static Role getAuthority(String authority) {
        return stream(values())
                .filter(role -> role.authority.equals(authority))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No user role - " + authority));
    }

    public static Set<String> stringRoles() {
        return stream(values())
                .map(Role::name)
                .collect(toSet());
    }

}
