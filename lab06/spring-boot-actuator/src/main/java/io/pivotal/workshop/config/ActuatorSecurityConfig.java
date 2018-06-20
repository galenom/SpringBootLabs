package io.pivotal.workshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ActuatorSecurityConfig  {

    @Autowired
    protected void configureUser(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("{noop}password").roles("ADMIN");
    }


    // To disable security do the following

    @Configuration
    @Order(1)
    public static class ActuatorSecurity extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/actuator/**") // 1. remove antMatcher
                    .authorizeRequests().anyRequest().permitAll();
        }

    }

    // 2. comment this whole class out.
    @Configuration
    public static class DefaultSecurity extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest().fullyAuthenticated()
                    .and()
                    .httpBasic();
        }
    }

}



