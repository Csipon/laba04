package com.kurachenko.controller.login;

import com.kurachenko.entity.Administrator;
import com.kurachenko.entity.Customer;
import com.kurachenko.entity.Employee;
import com.kurachenko.entity.ProjectManager;
import com.kurachenko.entity.intarface.User;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.*;
import com.kurachenko.service.daoimpl.factory.OracleDaoFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.Map;

/**
 * @author Pavel Kurachenko
 * @since 3/3/2017
 */
public class CustomLoginModule implements LoginModule {

    @Autowired
    private AdministratorService service;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProjectManagerService managerService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OracleDaoFactory factory;

    private CallbackHandler callbackHandler;
    private Subject subject;
    private boolean auth = false;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login :");
        callbacks[1] = new PasswordCallback("password :", false);
        try {
            callbackHandler.handle(callbacks);
        } catch (IOException | UnsupportedCallbackException e) {
            e.printStackTrace();
        }
        String login = ((NameCallback) callbacks[0]).getName();
        String password = new String(((PasswordCallback) callbacks[1]).getPassword());


        try(Connection connection = factory.getContext();
            PreparedStatement statement = connection.prepareStatement(loginRequest(login, password))) {

            ResultSet resultSet = statement.executeQuery();
            Integer id = null;
            String role = null;
            while (resultSet.next()) {
                id = resultSet.getInt("object_id");
                role = resultSet.getString("role");
            }

            if (role != null){
                User user = defineUser(role, id);
                if (user != null) {
                    subject.getPrincipals().add(user);
                    auth = true;
                }
            }else {
                throw new LoginException();
            }
            System.out.println("this 10");
        }catch (SQLException e){
            throw new LoginException(e.getMessage());
        } catch (PersistException e) {
            e.printStackTrace();
        }

        return auth;
    }

    @Override
    public boolean commit() throws LoginException {
        return auth;
    }

    @Override
    public boolean abort() throws LoginException {
        return auth;
    }

    @Override
    public boolean logout() throws LoginException {
        return true;
    }


    public String loginRequest(String login, String password ){
        Formatter formatter = new Formatter();
        formatter.format(StoreConstantForDB.SELECT_ALL, login, password, login, password, login);

        return formatter.toString();
    }

    private User defineUser(String role, Integer id) throws PersistException {
        if (role.equals(Administrator.class.getSimpleName())) {
            return service.getByPK(id);
        } else if (role.equals(Employee.class.getSimpleName())) {
            return employeeService.getByPK(id);
        }else if (role.equals(Customer.class.getSimpleName())) {
            return customerService.getByPK(id);
        } else if (role.equals(ProjectManager.class.getSimpleName())) {
            return managerService.getByPK(id);
        }
        return null;
    }
}
