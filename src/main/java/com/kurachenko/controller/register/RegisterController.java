package com.kurachenko.controller.register;

import com.kurachenko.entity.Employee;
import com.kurachenko.entity.enums.Qualification;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.DepartmentService;
import com.kurachenko.service.daoimpl.EmployeeService;
import com.kurachenko.service.daoimpl.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Pavel Kurachenko
 * @since 1/14/2017
 */
@Controller
public class RegisterController {
    @Autowired
    private DepartmentService deptService;
    @Autowired
    private EmployeeService empService;
    @Autowired
    private ProjectManagerService managerService;

    @RequestMapping(value = "/admin/registerCustomer", method = RequestMethod.GET)
    public String regCustomer(){
        return "admin/register/regcustomer";
    }


    @RequestMapping(value = "/admin/registry", method = RequestMethod.GET)
    public String getAll(Model model){
        try {
            model.addAttribute("departments", deptService.getAll());
            model.addAttribute("quality", Qualification.values());
        }catch (PersistException e) {
            e.printStackTrace();
        }
        return "admin/register/register";
    }

    @RequestMapping(value = "/admin/addWorker", method = RequestMethod.POST)
    public String addEmployee(String name, String surname, String description, String password, String login, @RequestParam Integer idDept, Boolean manager
            , String qualification, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("error");
            Employee employee;
            if (manager != null && manager) {
                employee = managerService.create();
            } else {
                employee = empService.create();
            }
            employee.setFirstName(name);
            employee.setLastName(surname);
            employee.setDescription(description);
            employee.setPassword(password);
            employee.setLogin(login);
            employee.setHiredate(new Date());
            employee.setLevelQualification(Qualification.valueOf(qualification));
            if (idDept != null) {
                employee.setDepartment(deptService.getByPK(idDept));
            }
            empService.update(employee);
            managerService.commit();
            return "redirect:/admin/profileAdmin";
        }catch (PersistException | SQLException e){
            try {
                managerService.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        }
        return "404";
    }
}
