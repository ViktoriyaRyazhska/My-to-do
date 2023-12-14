package com.softserve.itacademy.config.security;

import com.softserve.itacademy.model.User;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class WebAuthentication implements Authentication {

    private final User user;
    private boolean isAuthenticated;

    public WebAuthentication(User user) {
        this.user = user;
        isAuthenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    public User getUser() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        //TODO
    }

    @Override
    public String getName() {
        return null;
    }

}
