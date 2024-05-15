package fit.se2.hanulms.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LecturerDetails implements UserDetails {
    private Lecturer lecturer;

    public LecturerDetails(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] roles = lecturer.getRole().split(",");
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return lecturer.getPassword();
    }

    @Override
    public String getUsername() {
        return lecturer.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
