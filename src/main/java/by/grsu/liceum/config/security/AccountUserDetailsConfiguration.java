package by.grsu.liceum.config.security;

import by.grsu.liceum.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AccountUserDetailsConfiguration implements UserDetails {
    private final String login;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;

    public AccountUserDetailsConfiguration(Account account){
        this.login = account.getLogin();
        this.password = account.getPassword();
        this.authorities = account.getRoles().stream()
                .map(x -> new SimpleGrantedAuthority(x.getName()))
                .toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public String getPassword() {
        return this.password;
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
