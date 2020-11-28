package com.slutprojeeram.slutprojee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService ServiceClass() {

        return new CustomService();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean
    public DaoAuthenticationProvider authentication() {

        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();

        auth.setUserDetailsService(ServiceClass());
        auth.setPasswordEncoder(passwordEncoder());

        return auth;

    }

    // Authorizatio configuration


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(authentication());

    }
    private static final String[] PUBLIC_MATCH = {
            "/css/**",
            "/js/**",
            "/image/**",
            "/home",
            "/",
            "/artShelf",
            "/login",
            "/loginAdmin"
    };

    private static final String[] ADMIN_MATCH = {
            "/css/**",
            "/js/**",
            "/image/**",
            "/",
            "/home",
            "/artShelf",

    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers(ADMIN_MATCH).hasRole("ADMIN")
                .antMatchers(PUBLIC_MATCH).hasRole("USER")
                .antMatchers(PUBLIC_MATCH).permitAll()
                .and().csrf().disable();
    }



}
