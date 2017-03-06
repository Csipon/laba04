package com.kurachenko.config.details;

import com.kurachenko.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

public class EmployeeDetails extends Employee implements UserDetails {

    private static final long serialVersionUID = 1L;
    private List<String> userRoles;


    public EmployeeDetails(Employee employee,List<String> userRoles){
        super(employee);
        this.userRoles=userRoles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String roles= StringUtils.collectionToCommaDelimitedString(userRoles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String getUsername() {
        return super.getName();
    }

}

