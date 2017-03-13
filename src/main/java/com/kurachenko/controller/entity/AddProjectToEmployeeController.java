package com.kurachenko.controller.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurachenko.entity.Employee;
import com.kurachenko.entity.Project;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.daohelpers.EmployeeHelper;
import com.kurachenko.service.daoimpl.EmployeeService;
import com.kurachenko.service.daoimpl.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Servlet for show employee which are not yet added on project
     * if all good then redirect on project page, else go on error page
     * @param idProject current project on which need add employee
     * */
    @RequestMapping(value = "admin/showEmpsFreeProject", method = RequestMethod.GET)
    public void showEmpsFreeProject(HttpServletResponse response, Integer idProject){
        try {
            List<Employee> result = EmployeeHelper.getFreeEmps(employeeService.getAll(), idProject);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(mapper.writeValueAsString(result));
        } catch (PersistException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Servlet for set specific employee on project
     * if all good then redirect on project page, else go on error page
     * @param idProject current project on which need add employee
     * @param idEmployee employee which need add on project
     * */
    @RequestMapping(value = "/admin/setEmpToProject", method = RequestMethod.POST)
    public void setEmpToProject(HttpServletResponse response, Integer idEmployee, Integer idProject){
        try {
            Project project = projectService.getByPK(idProject);
            Employee employee = employeeService.getByPK(idEmployee);
            employee = EmployeeHelper.setProject(employee, project);
            employeeService.update(employee);
            employeeService.commit();
            response.setCharacterEncoding("UTF-8");
        }catch (PersistException | SQLException e) {
            try {
                employeeService.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
