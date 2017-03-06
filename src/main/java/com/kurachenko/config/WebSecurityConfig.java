package com.kurachenko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("SELECT \n" +
                "object_id, " +
                "name as password,\n" +
                "value as login \n" +
                "FROM params p\n" +
                "where value = ? and name = 'login'")
            .authoritiesByUsernameQuery("select object_id, 'ROLE_ProjectManager' as role from params where value = ? and name = 'login'");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_Administrator')")
                .antMatchers("/manager/**").access("hasRole('ROLE_ProjectManager')")
                .antMatchers("/employee/**").access("hasRole('ROLE_Employee')")
                .antMatchers("/customer/**").access("hasRole('ROLE_Customer')")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .usernameParameter("login").passwordParameter("password")
//                .successHandler(successHandler)
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
        ;

        http.csrf();
    }
}