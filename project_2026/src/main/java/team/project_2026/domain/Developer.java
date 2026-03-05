package team.project_2026.domain;

import java.util.Collection;
import java.util.Collections;

import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
public class Developer implements UserDetails {

    @Id
    @Column(name="username", unique=true)
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    public Developer() {
        super();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // όλοι οι χρήστες έχουν το ίδιο authority
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override public boolean isAccountNonExpired() {
        return true;
    }
    @Override public boolean isAccountNonLocked() {
        return true;
    }
    @Override public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}