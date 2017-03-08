package com.kurachenko.config;

import com.kurachenko.config.details.AdminDetails;
import com.kurachenko.config.details.CustomerDetails;
import com.kurachenko.config.details.EmployeeDetails;
import com.kurachenko.config.details.ManagerDetails;
import com.kurachenko.entity.Administrator;
import com.kurachenko.entity.Customer;
import com.kurachenko.entity.Employee;
import com.kurachenko.entity.ProjectManager;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.*;
import com.kurachenko.service.daoimpl.factory.OracleDaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private OracleDaoFactory factory;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProjectManagerService managerService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AdministratorService administratorService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDetails user = null;
        try {
            user = getByLogin(login);
        } catch (SQLException | PersistException e) {
            e.printStackTrace();
        }
        if (user == null){
            throw new UsernameNotFoundException("No user present with login: " + login);
        }
        return user;
    }

    private UserDetails getByLogin(String login) throws SQLException, PersistException {
        PreparedStatement statement = factory.getContext().prepareStatement(StoreConstantForDB.SELECT_LOGIN_PASSWORD);
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        Integer id = resultSet.getInt("object_id");
        UserDetails user = getSpecificUser(getRole(id), id);

        return user;
    }

    private String getRole(Integer id) throws SQLException {
        PreparedStatement statementRole = factory.getContext().prepareStatement(StoreConstantForDB.SELECT_OBJECT_ROLE);
        statementRole.setInt(1, id);
        ResultSet resultSetRole = statementRole.executeQuery();
        resultSetRole.next();
        String role = resultSetRole.getString("role");
        return role;
    }

    private UserDetails getSpecificUser(String role, Integer id) throws PersistException {
        UserDetails user = null;
        String prefix = "ROLE_";
        if (Administrator.class.getSimpleName().equalsIgnoreCase(role)){
            user = new AdminDetails(administratorService.getByPK(id), Arrays.asList(prefix + role));
        }else if (ProjectManager.class.getSimpleName().equalsIgnoreCase(role)){
            user = new ManagerDetails(managerService.getByPK(id), Arrays.asList(prefix + role));
        }else if (Customer.class.getSimpleName().equalsIgnoreCase(role)){
            user = new CustomerDetails(customerService.getByPK(id), Arrays.asList(prefix + role));
        }else if (Employee.class.getSimpleName().equalsIgnoreCase(role)){
            user = new EmployeeDetails(employeeService.getByPK(id), Arrays.asList(prefix + role));
        }
        return user;
    }

}


