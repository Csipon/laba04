package com.kurachenko.config;

import com.kurachenko.config.details.AdminDetails;
import com.kurachenko.config.details.CustomerDetails;
import com.kurachenko.config.details.EmployeeDetails;
import com.kurachenko.config.details.ManagerDetails;
import com.kurachenko.entity.Administrator;
import com.kurachenko.entity.Customer;
import com.kurachenko.entity.Employee;
import com.kurachenko.entity.ProjectManager;
import com.kurachenko.entity.intarface.User;
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
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;


@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private OracleDaoFactory factory;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProjectManagerService managerService;
    @Autowired
    private AdministratorService administratorService;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = null;
        try {
            user = getByLogin(login);
        } catch (SQLException | PersistException e) {
            e.printStackTrace();
        }
        if(user == null){
            throw new UsernameNotFoundException("No user present with login: "+ login);
        }else{
            List<String> userRoles = new ArrayList<>();
            userRoles.add(user.getClass().getSimpleName());
            if (user instanceof Administrator){
                return new AdminDetails((Administrator) user,userRoles);
            }else if (user instanceof ProjectManager){
                return new ManagerDetails((ProjectManager) user,userRoles);
            }else if (user instanceof Customer){
                return new CustomerDetails((Customer) user,userRoles);
            }else if (user instanceof Employee){
                return new EmployeeDetails((Employee) user,userRoles);
            }
            return null;
        }
    }

    private User getByLogin(String login) throws SQLException, PersistException {
        PreparedStatement statement = factory.getContext().prepareStatement(loginRequest(login));
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        Integer id = resultSet.getInt("object_id");
        String role = resultSet.getString("role");

        System.out.println("Good 5");

        User user = null;
        if (Administrator.class.getSimpleName().equals(role)) {
            user = administratorService.getByPK(id);
        } else if (Employee.class.getSimpleName().equals(role)) {
            user = employeeService.getByPK(id);
        }else if (Customer.class.getSimpleName().equals(role)) {
            user = customerService.getByPK(id);
        } else if (ProjectManager.class.getSimpleName().equals(role)) {
            user = managerService.getByPK(id);
        }
        return user;
    }

    public String loginRequest(String login){
        Formatter formatter = new Formatter();
        formatter.format(StoreConstantForDB.SELECT_BY_LOGIN, login, login, login);
        return formatter.toString();
    }


}

