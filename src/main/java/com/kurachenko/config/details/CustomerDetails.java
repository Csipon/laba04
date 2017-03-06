package com.kurachenko.config.details;

import com.kurachenko.entity.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 3/4/2017
 */
public class CustomerDetails extends Customer implements UserDetails {


    private static final long serialVersionUID = 1L;
    private List<String> userRoles;


    public CustomerDetails(Customer customer,List<String> userRoles){
        super(customer);
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
