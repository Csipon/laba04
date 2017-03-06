package com.kurachenko.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurachenko.entity.Employee;
import com.kurachenko.entity.Project;
import com.kurachenko.entity.ProjectManager;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.daohelpers.AdminHelper;
import com.kurachenko.service.daoimpl.EmployeeService;
import com.kurachenko.service.daoimpl.ProjectManagerService;
import com.kurachenko.service.daoimpl.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * controller for admin and managed admin data
 * @author Pavel Kurachenko
 * @since 1/19/2017
 */
@Controller
public class AdminController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProjectManagerService managerService;
    @Autowired
    private ProjectService projectService;

    /**
     * Mapper for convert object in JSON format
     * */
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Servlet for assign simple employee manager, and go to list managers
     * @param empId id employee which will be made manager
     * @param model need for insert our manager as attribute
     * */
    @RequestMapping(value = "/assignManager", method = RequestMethod.POST)
    public String assignManager(Integer empId, Model model) {

        try {
            Employee emp = employeeService.getByPK(empId);
            ProjectManager manager = managerService.create();
            manager.setDepartment(emp.getDepartment());
            manager.setFirstName(emp.getName());
            manager.setLastName(emp.getSurname());
            manager.setDescription(emp.getDescription());
            manager.setHiredate(emp.getHiredate());
            manager.setPassword(emp.getPassword());
            manager.setLogin(emp.getLogin());
            manager.setProjects(emp.getProjects());
            employeeService.delete(emp);
            managerService.update(manager);
            model.addAttribute("manager", manager);
            managerService.commit();
        }catch (PersistException | SQLException e) {
            try {
                managerService.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return "admin/manager/managers";
    }

    /**
     * Servlet for add manager on project, if manager added on project successful
     * then go on admin main page, else go on error page 403
     * @param idManager id manager which need add on project
     * @param idProject id project on which need add manager
     * */
    @RequestMapping(value = "/addManagerToProject", method = RequestMethod.POST)
    public String addManagerToProject(Integer idManager, Integer idProject)  {
        try {
            if (idManager != null && idProject != null) {
                ProjectManager manager = managerService.getByPK(idManager);
                Project project = projectService.getByPK(idProject);
                AdminHelper.addManagedProject(manager, project);
                managerService.update(manager);
                managerService.commit();
            }
            return "redirect:/admin";
        } catch (PersistException | SQLException e) {
            try {
                managerService.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return "403";
    }

    /**
     * Servlet for add free employees as subordinates to manager,
     * @param idManager manager to which will be add subordinates
     *  */
    @RequestMapping(value = "/loadFreeEmps", method = RequestMethod.GET)
    public void loadFreeEmps(Integer idManager, HttpServletResponse response)  {
        try {
            ProjectManager manager = managerService.getByPK(idManager);
            List<Integer> subordinates = AdminHelper.listId(manager.getSubordinates());
            List<Employee> result = new ArrayList<>();
            for (Employee employee : employeeService.getAll()){
                if (!subordinates.contains(employee.getId())){
                    result.add(employee);
                }
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(mapper.writeValueAsString(result));
        } catch (PersistException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add one employee as subordinate of manager, if all good redirect on current manager,
     * else go to error page 403
     * need will remake this servlet and above "/loadFreeEmps"
     * @param idEmployee employee which will be subordinate in manager
     * @param idManager current manager to which will be added subordinate
     * */
    @RequestMapping(value = "/addSubordinateToManager", method = RequestMethod.GET)
    public void addSubordinateToManager(Integer idEmployee, Integer idManager, HttpServletResponse response)  {
        try {
            if (idManager != null && idEmployee != null) {
                ProjectManager manager = managerService.getByPK(idManager);
                Employee employee = employeeService.getByPK(idEmployee);
                AdminHelper.addSubordinate(manager, employee);

                managerService.update(manager);
                managerService.commit();
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Employee - " + employee.getName() + " " + employee.getSurname() +
                                                " is successful added");
            }
        } catch (PersistException | SQLException e) {
            try {
                managerService.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Servlet for valid login when admin register new employee
     * if login is valid then in response write as {@code String} value 'true',
     * else in response write as {@code String} value 'false'
     * @param login our login which we input
     * @param response need for write response
     * */
    @RequestMapping(value = "/valid", method = RequestMethod.GET)
    public void valid(String login, HttpServletResponse response) throws IOException {
        response.getWriter().write(String.valueOf(employeeService.validLogin(login)));
    }


    /**
     * Servlet for move on main admin page and clean session attribute 'employees'
     * @param request need for get current session
     * */
    @RequestMapping(value = "/toMain", method = RequestMethod.GET)
    public String toMain(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("employees");
        return "admin/main";
    }
}
