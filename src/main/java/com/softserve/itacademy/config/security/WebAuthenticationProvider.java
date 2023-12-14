package com.softserve.itacademy.config.security;

import com.softserve.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class WebAuthenticationProvider implements AuthenticationProvider {

    public WebAuthenticationProvider(UserService userService) {
        //TODO
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        //TODO
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //TODO
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
