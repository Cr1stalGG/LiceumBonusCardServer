package by.grsu.liceum.config.security;

import by.grsu.liceum.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AccountUserDetailsConfiguration implements UserDetails {
    private String login;
    private String password;
    private List<SimpleGrantedAuthority> authorities;

    public AccountUserDetailsConfiguration(Account account){
        this.login = account.getLogin();
        this.password = account.getPassword();
        this.authorities = account.getRoles().stream().map(x -> new SimpleGrantedAuthority(x.getName().name())).toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
