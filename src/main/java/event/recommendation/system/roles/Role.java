package event.recommendation.system.roles;

import org.springframework.security.core.GrantedAuthority;

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


}
