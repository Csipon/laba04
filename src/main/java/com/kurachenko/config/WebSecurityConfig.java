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
        auth.userDetailsService(userDetailsService);
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("SELECT p1.object_id, p2.value as password, p1.value as login " +
                "FROM params p1 inner JOIN params p2 on p2.OBJECT_ID = p1.OBJECT_ID and p2.name = 'password' " +
                "where p1.value = ?")
            .authoritiesByUsernameQuery("SELECT p.value as login, ('ROLE_' || o.name) as role FROM objects o inner join params p on p.OBJECT_ID = o.OBJECT_ID WHERE p.value = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").access("ROLE_Administrator")
                .antMatchers("/manager/**").access("ROLE_ProjectManager")
                .antMatchers("/employee/**").access("ROLE_Employee")
                .antMatchers("/customer/**").access("ROLE_Customer")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .usernameParameter("login").passwordParameter("password")
                .successHandler(successHandler)
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
        ;

        http.csrf();
    }
}