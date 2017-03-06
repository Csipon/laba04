package com.kurachenko.controller.entity;

import com.kurachenko.entity.Employee;
import com.kurachenko.entity.Project;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.daohelpers.EmployeeHelper;
import com.kurachenko.service.daoimpl.EmployeeService;
import com.kurachenko.service.daoimpl.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller for add employee on project
 * @author Pavel Kurachenko
 * @since 1/27/2017
 */
@Controller
public class AddProjectToEmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProjectService projectService;

    /**
     * Servlet for show employee which are not yet added on project
     * if all good then redirect on project page, else go on error page
     * @param idProject current project on which need add employee
     * @param request need for get session and after set in session as attribute list
     *                free employees
     * @param model need for add as attribute object project
     * */
    @RequestMapping(value = "/showEmpsFreeProject", method = RequestMethod.GET)
    public String showEmpsFreeProject(HttpServletRequest request, Integer idProject, Model model){
        try {
            List<Employee> list = employeeService.getAll();
            List<Employee> result = EmployeeHelper.getFreeEmps(list, idProject);

            HttpSession session = request.getSession();
            session.setAttribute("employees", result);
            model.addAttribute("project", projectService.getByPK(idProject));
            employeeService.commit();
            return "redirect:/idProject?id=" + idProject;
        } catch (PersistException | SQLException e) {
            try {
                employeeService.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return "403";
    }

    /**
     * Servlet for set specific employee on project
     * if all good then redirect on project page, else go on error page
     * @param idProject current project on which need add employee
     * @param idEmployee employee which need add on project
     * @param model need for add as attribute object project
     * @param request need for get session and after from session get list employees
     * */
    @RequestMapping(value = "/setEmpToProject", method = RequestMethod.GET)
    public String setEmpToProject(HttpServletRequest request, Integer idEmployee, Integer idProject, Model model){
        HttpSession session = request.getSession();
        try {
            Project project = projectService.getByPK(idProject);
            Employee employee = employeeService.getByPK(idEmployee);

            employee = EmployeeHelper.setProject(employee, project);

            List<Employee> emps = (List<Employee>) session.getAttribute("employees");
            session.setAttribute("employees", EmployeeHelper.removeEmployee(emps, employee));

            model.addAttribute("project", project);
            employeeService.update(employee);
            employeeService.commit();
            return "redirect:/idProject?id=" + project.getId();
        }catch (PersistException | SQLException e) {
            try {
                employeeService.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return "404";
    }
}
