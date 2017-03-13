package com.kurachenko.controller.update;

import com.kurachenko.entity.Customer;
import com.kurachenko.entity.Employee;
import com.kurachenko.entity.ProjectManager;
import com.kurachenko.entity.Sprint;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

/**
 * @author Pavel Kurachenko
 * @since 1/21/2017
 */
@Controller
public class UpdateController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProjectManagerService managerService;
    @Autowired
    private DepartmentService deptService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SprintService sprintService;

    @RequestMapping(value = "/admin/getEmpUpdate", method = RequestMethod.GET)
    public String getEmpUpdate(@RequestParam Integer id, Model model){
        try{
            model.addAttribute("emp", employeeService.getByPK(id));
            model.addAttribute("departments", deptService.getAll());
        }catch (PersistException e){
            e.getMessage();
        }
        return "admin/employee/update";
    }

    @RequestMapping(value = "/manager/getSprintUpdate", method = RequestMethod.GET)
    public String getSprintUpdate(@RequestParam Integer id, Model model){
        try{
            model.addAttribute("sprint", sprintService.getByPK(id));
            model.addAttribute("sprints", sprintService.getAll());
        }catch (PersistException e){
            e.getMessage();
        }
        return "manager/update/sprint";
    }


    @RequestMapping(value = "/manager/updateSprint", method = RequestMethod.POST)
    public String update(Sprint sprint, @RequestParam  Integer idPreviousSprint, Errors errors)
    {
        if (errors.hasErrors()) {
            return "manager/main";
        }
        try {
            Sprint temp = sprintService.getByPK(sprint.getId());
            temp.setName(sprint.getName());
            temp.setDescription(sprint.getDescription());
            if (idPreviousSprint == null){
                temp.setPreviousSprint(null);
            }else {
                temp.setPreviousSprint(sprintService.getByPK(idPreviousSprint));
            }
            sprintService.update(temp);
            sprintService.commit();
        } catch (PersistException | SQLException e){
            try {
                sprintService.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        }
        return "403";
    }


    @RequestMapping(value = "/admin/updateEmployee", method = RequestMethod.POST)
    public String update(Employee employee, @RequestParam  Integer idDept, boolean manager, Errors errors)
    {
        if (errors.hasErrors()) {
            return "admin/register/register";
        }
        try {
            Employee temp;
            if (manager){
                temp = managerService.create();
            }else {
                temp = employeeService.getByPK(employee.getId());
            }
            temp.setFirstName(employee.getName());
            temp.setLastName(employee.getSurname());
            temp.setPassword(employee.getPassword());
            temp.setDescription(employee.getDescription());
            temp.setDepartment(deptService.getByPK(idDept));
            if(manager){
                managerService.update((ProjectManager) temp);
                managerService.commit();
                return "admin/manager/managers";
            }else {
                employeeService.update(temp);
                employeeService.commit();
                return "admin/employee/employees";
            }
        } catch (PersistException | SQLException e){
            try {
                employeeService.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        }
        return "403";
    }

    @RequestMapping(value = "/admin/getManagerUpdate", method = RequestMethod.GET)
    public String getManagerUpdate(@RequestParam Integer id, Model model){
        try{
            model.addAttribute("manager", managerService.getByPK(id));
            model.addAttribute("departments", deptService.getAll());
        }catch (PersistException e){
            e.getMessage();
        }
        return "admin/manager/update";
    }


    @Transactional
    @RequestMapping(value = "/admin/updateManager", method = RequestMethod.POST)
    public String update(ProjectManager manager, @RequestParam  Integer idDept, Errors errors)
    {
        if (errors.hasErrors()) {
            return "admin/register/register";
        }
        try {
            ProjectManager temp = managerService.getByPK(manager.getId());
            temp.setFirstName(manager.getName());
            temp.setLastName(manager.getSurname());
            temp.setPassword(manager.getPassword());
            temp.setDescription(manager.getDescription());
            temp.setDepartment(deptService.getByPK(idDept));
            managerService.update(temp);
            managerService.commit();
            return "admin/manager/managers";
        } catch (PersistException | SQLException e){
            try {
                managerService.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        }
        return "403";
    }

    @RequestMapping(value = "/admin/getCustomerUpdate", method = RequestMethod.GET)
    public String getCustomerUpdate(@RequestParam Integer id, Model model){
        try{
            model.addAttribute("customer", customerService.getByPK(id));
        }catch (PersistException e){
            e.getMessage();
        }
        return "admin/customer/update";
    }


    @RequestMapping(value = "/admin/updateCustomer", method = RequestMethod.POST)
    public String update(Customer customer, Errors errors)
    {
        if (errors.hasErrors()) {
            return "admin/customer/customers";
        }
        try {
            Customer temp = customerService.getByPK(customer.getId());
            temp.setName(customer.getName());
            temp.setSurname(customer.getSurname());
            temp.setPassword(customer.getPassword());
            temp.setDescription(customer.getDescription());
            temp.setCompanyName(customer.getCompanyName());
            customerService.update(temp);
            customerService.commit();
            return "admin/customer/customers";
        } catch (PersistException | SQLException e){
            try {
                customerService.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        }
        return "403";
    }
}
