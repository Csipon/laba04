package com.kurachenko.controller.login;

import com.kurachenko.entity.Administrator;
import com.kurachenko.entity.Customer;
import com.kurachenko.entity.Employee;
import com.kurachenko.entity.ProjectManager;
import com.kurachenko.entity.intarface.User;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.daohelpers.CheckHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * @author Pavel Kurachenko
 * @since 3/4/2017
 */
@Controller
public class SecureController {
    private static final String PAGE_ERROR = "403";

    static {

    }

    @RequestMapping(value = "/loging", method = RequestMethod.POST)
    public String login(HttpServletRequest req){
        System.setProperty("java.security.auth.login.config", "D:\\laba03\\src\\main\\java\\jaas.config");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        try {
            session.setMaxInactiveInterval(480);
            session.removeAttribute("msg");
            session.removeAttribute("error");
            System.out.println("this -1");
            if (login != null && login.length() != 0){
                if(password != null && password.length() != 0) {
                    password = CheckHelper.passwordCode(password);
                    MyCallbackHandler handler = new MyCallbackHandler(login, password);
                    LoginContext loginContext = new LoginContext("MyJaasConfig", handler);
                    loginContext.login();
                    System.out.println("this 0");
                    User user = null;
                    for (Principal p : loginContext.getSubject().getPrincipals()){
                        user = (User) p;
                        break;
                    }
                    System.out.println("this 1");

                    session.setAttribute("user", user);
                    if (user instanceof ProjectManager){
                        session.setAttribute("subordinates", ((ProjectManager)user).getSubordinates());
                        System.out.println("this 2");
                    }
                    System.out.println("this 3");

                    return setRoleAndRedirect(user, session);
                }else {
                    session.setAttribute("error", "please enter password!");
                    return "/login";
                }
            }else {
                session.setAttribute("error", "please enter login!");
                return "/login";
            }
        } catch (LoginException e) {
            session.setAttribute("error", "You entered incorrect data!");
            return "/login";
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return PAGE_ERROR;
    }

    private String setRoleAndRedirect(User user, HttpSession session){
        System.out.println("this 4");

        if (user instanceof ProjectManager){
            session.setAttribute("role", "MANAGER");
            System.out.println("this 5");

            return "redirect:/profileMgr";
        }else if (user instanceof Employee){
            session.setAttribute("role", "EMPLOYEE");
            return "redirect:/profileEmp";
        }else if (user instanceof Administrator){
            session.setAttribute("role", "ADMIN");
            return "redirect:/admin";
        }else if (user instanceof Customer){
            session.setAttribute("role", "CUSTOMER");
            return "redirect:/profileCustomer";
        }else {
            return "404";
        }
    }
}
