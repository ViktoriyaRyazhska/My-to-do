package com.softserve.itacademy.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class WebSecurityConfigurer {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

//    private final WebAuthenticationFilter authenticationFilter;
//
//    @Autowired
//    public WebSecurityConfigurer(WebAuthenticationFilter authenticationFilter) {
//        this.authenticationFilter = authenticationFilter;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        http.exceptionHandling(customizer -> customizer
//                .authenticationEntryPoint(
//                        (request, response, authException) -> response.sendRedirect(request.getContextPath() + "/login"))
//        );

        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.GET, "/").permitAll()
                .requestMatchers(HttpMethod.GET, "/login").permitAll()
                .requestMatchers("/users/create").permitAll()
                .anyRequest().authenticated()
        );

        http.formLogin(login ->
                login.loginPage("/login").permitAll()
        );

//        http.addFilter(new WebAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
//        http.userDetailsService(domainUserDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
