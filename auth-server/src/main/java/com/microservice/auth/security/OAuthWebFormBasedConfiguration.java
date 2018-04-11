package com.microservice.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OAuthWebFormBasedConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/oauth/confirm_access").setViewName("authorize");
    }

    @Configuration
    @Order(-20)
    protected static class LoginConfig extends WebSecurityConfigurerAdapter {

        private final AuthenticationManager authenticationManager;

        @Autowired
        public LoginConfig(AuthenticationManager authenticationManager) {
            this.authenticationManager = authenticationManager;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http.formLogin().loginPage("/login").permitAll().and().requestMatchers()
                    .antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access").and().authorizeRequests()
                    .anyRequest().authenticated();
            // @formatter:on
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.parentAuthenticationManager(authenticationManager);
        }
    }
}