package com.kurachenko.controller.login;

import com.kurachenko.entity.Administrator;
import com.kurachenko.entity.Customer;
import com.kurachenko.entity.Employee;
import com.kurachenko.entity.ProjectManager;
import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.daohelpers.CheckHelper;
import com.kurachenko.service.daoimpl.*;
import com.kurachenko.service.daoimpl.factory.OracleDaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;

/**
 * Controller for login
 * @author Pavel Kurachenko
 * @since 1/19/2017
 */
@Controller
public class LoginController {
    private static final String PAGE_ERROR = "403";

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

    /**
     * Servlet for login user, from request we get login and password
     *
     * */
    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public String login(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(480);
        session.removeAttribute("msg");
        session.removeAttribute("error");
        try {
            if (login != null && login.length() != 0){
                if(password != null && password.length() != 0) {
                    password = CheckHelper.passwordCode(password);
                    Connection connection = factory.getContext();
                    PreparedStatement statement = connection.prepareStatement(loginRequest(login, password));
                    ResultSet resultSet = statement.executeQuery();

                    Integer id = null;
                    String role = null;
                    while (resultSet.next()) {
                        id = resultSet.getInt("object_id");
                        role = resultSet.getString("role");
                    }


                    connection.close();
                    Identified object = null;
                    String modelName = null;
                    if (role != null) {
                        return defineUser(role, object, modelName, id, session);
                    }else {
                        session.setAttribute("error", "You entered incorrect data!");
                        return "/login";
                    }
                }else {
                    session.setAttribute("error", "please enter password!");
                    return "/login";
                }
            }else {
                session.setAttribute("error", "please enter login!");
                return "/login";
            }
        } catch (PersistException e) {
            session.setAttribute("error", "You entered incorrect data!");
            return "/login";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return PAGE_ERROR;
    }

    /**
     * method need for create string with statement use entered user login and password
     * */
    public String loginRequest(String login, String password ){
        Formatter formatter = new Formatter();
        formatter.format(StoreConstantForDB.SELECT_ALL, login, password, login, password, login);

        return formatter.toString();
    }

    /**
     * Method for define right user and set attributes in session
     * @param role this role define type user
     * @param object this common object all depend from role how will be type in object
     * @param modelName this is name assign in depend object type
     * @param id this is id of object
     * @param session need for add attributes
     * @return string in depend from model name value
     * */
    private String defineUser(String role, Identified object, String modelName, Integer id, HttpSession session) throws PersistException {
        if (role.equals(Administrator.class.getSimpleName())) {
            object = service.getByPK(id);
            modelName = "ADMIN";
        } else if (role.equals(Employee.class.getSimpleName())) {
            object = employeeService.getByPK(id);
            modelName = "EMPLOYEE";
        }else if (role.equals(Customer.class.getSimpleName())) {
            object = customerService.getByPK(id);
            modelName = "CUSTOMER";
        } else if (role.equals(ProjectManager.class.getSimpleName())) {
            object = managerService.getByPK(id);
            modelName = "MANAGER";
            session.setAttribute("subordinates", ((ProjectManager)object).getSubordinates());
        }
        session.setAttribute("user", object);
        session.setAttribute("role", modelName);

        if ("ADMIN".equals(modelName)){
            return "redirect:/admin";
        }else if ("EMPLOYEE".equals(modelName)){
            return "redirect:/profileEmp";
        }else if ("CUSTOMER".equals(modelName)){
            return "redirect:/profileCustomer";
        }else if ("MANAGER".equals(modelName)){
            return "redirect:/profileMgr";
        }else {
            return "403";
        }
    }
}
