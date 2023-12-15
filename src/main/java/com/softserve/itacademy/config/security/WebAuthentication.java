package com.softserve.itacademy.config.security;

import lombok.Data;

import java.util.Collection;

@Data
public class WebAuthentication  {


    public Collection<?> getAuthorities() {
        return null;
    }

    public Object getCredentials() {
        return null;
    }

    public Object getDetails() {
        return null;
    }

    public Object getPrincipal() {
        return null;
    }

    public boolean isAuthenticated() {
        return false;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    }

    public String getName() {
        return null;
    }
}
