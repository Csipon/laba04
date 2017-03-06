package com.kurachenko.controller.entity;

import com.kurachenko.entity.Project;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.CustomerService;
import com.kurachenko.service.daoimpl.ProjectManagerService;
import com.kurachenko.service.daoimpl.ProjectService;
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
 * Controller which handle all request which bound with project
 * @author Pavel Kurachenko
 * @since 1/13/2017
 */

@Controller
public class ProjectController {
    @Autowired
    private ProjectService service;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProjectManagerService managerService;


    /**
     * Servlet for prepare to create new project
     * */
    @RequestMapping(value = "/createProject", method = RequestMethod.GET)
    public String newProject(Model model){
        try {
            model.addAttribute("customers", customerService.getAll());
        }catch (PersistException e) {
            e.printStackTrace();
        }
        return "admin/register/project";
    }


    /**
     * Servlet for get all projects
     * */
    @RequestMapping(value = "/getAllProject", method = RequestMethod.GET)
    public String getAll(Model model){
        try {
            model.addAttribute("projectList", service.getAll());
        }catch (PersistException e) {
            e.printStackTrace();
        }
        return "admin/project/projects";
    }


    /**
     * Servlet for add new project, method POST and redirect to all project
     * @param project our project which we filled in form
     * @param finishP this is Date in numeric format
     * @param idCustomer this is id customer which ordered project
     * */
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public String addProject(Project project, Long finishP, Integer idCustomer)
    {
        try {
            Project temp = service.create();
            temp.setCustomer(customerService.getByPK(idCustomer));
            temp.setName(project.getName());
            temp.setPlanedBudget(project.getPlanedBudget());
            temp.setDescription(project.getDescription());
            temp.setAdditionalPayments(project.getAdditionalPayments());
            temp.setPaid(project.getPaid());
            temp.setStart(new Date());
            temp.setFinish(new Date(finishP));
            service.update(temp);
            service.commit();
        }catch (PersistException | SQLException e){
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        }
        return "redirect:/getAllProject";
    }


    /**
     * Servlet for get project by id
     * */
    @RequestMapping(value = "/idProject", method = RequestMethod.GET)
    public String information(@RequestParam Integer id, Model model, HttpServletRequest request){

        try{
            Project project = service.getByPK(id);
            model.addAttribute("project", project);
            model.addAttribute("managers",managerService.getAll());
            HttpSession session = request.getSession();
            session.setAttribute("idProject", project.getId());
        }catch (PersistException e){
            e.getMessage();
        }
        return "project/project";
    }


    /**
     * Servlet for delete project
     * @param id project id
     * @param confirm boolean variable which confirm delete project
     * @return if all successful redirect to all projects else redirect on page 403
     * */
    @RequestMapping(value = "/deleteProject", method = RequestMethod.GET)
    public String delete(Integer id, Boolean confirm){

        try{
            Project project = service.getByPK(id);
            if (project != null){
                if (confirm){
                    service.delete(project);
                    service.commit();
                    return "redirect:/getAllProject";
                }
            }
        }catch (PersistException | SQLException e){
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        }
        return "403";
    }
}
