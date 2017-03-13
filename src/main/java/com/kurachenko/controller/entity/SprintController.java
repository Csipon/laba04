package com.kurachenko.controller.entity;

import com.kurachenko.entity.Journal;
import com.kurachenko.entity.Project;
import com.kurachenko.entity.Sprint;
import com.kurachenko.entity.Task;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.daohelpers.SprintHelper;
import com.kurachenko.service.daoimpl.JournalService;
import com.kurachenko.service.daoimpl.ProjectService;
import com.kurachenko.service.daoimpl.SprintService;
import com.kurachenko.service.daoimpl.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;

/**
 * Controller which handle all request which bound with sprint
 * @author Pavel Kurachenko
 * @since 1/23/2017
 */
@Controller
public class SprintController {

    @Autowired
    private SprintService service;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private JournalService journalService;
    @Autowired
    private TaskService taskService;

    /**
     * Servlet for prepare to create sprint
     * @param idProject this id project for which need create sprint
     * @return if all good go to sprint.jsp else go to 403 error page
     * */
    @RequestMapping(value = "/manager/toCreateSprint", method = RequestMethod.GET)
    public String prepare(Model model, Integer idProject) {
        try {
            Sprint[] sprints = projectService.getByPK(idProject).getSprints();
            model.addAttribute("sprints", sprints);
            model.addAttribute("idProject", idProject);
            return "manager/create/sprint";
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return "403";
    }


    /**
     * Servlet for add new sprint
     * @param sprint this our entity which we filled in sprint form
     * @param idProject this id project in which need add new sprint
     * @param idPreviousSprint this id previous sprint from which depend work on this sprint
     * @return if all good redirect on project on which we created new sprint, else go to error page 403
     * */
    @RequestMapping(value = "/manager/addSprint", method = RequestMethod.POST)
    public String create(Sprint sprint, Integer idProject, Integer idPreviousSprint) {
        try {
            Sprint temp = service.create();
            temp.setName(sprint.getName());
            temp.setDescription(sprint.getDescription());
            temp.setCreated(new Date());
            if (idPreviousSprint != null) {
                temp.setPreviousSprint(service.getByPK(idPreviousSprint));
            }
            service.update(temp);
            Project project = projectService.getByPK(idProject);
            SprintHelper.setSprintInProject(project, temp);
            projectService.update(project);
            service.commit();
            return "redirect:/idProject?id=" + idProject;
        } catch (PersistException | SQLException e){
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        }
        return "403";
    }


    /**
     * Servlet for get sprint by id
     * */
    @RequestMapping(value = "/maker/idSprint", method = RequestMethod.GET)
    public String getByID(@RequestParam  Integer id, Model model, HttpServletRequest request) {
        try {
            Sprint sprint = service.getByPK(id);
            model.addAttribute("sprint", sprint);
            model.addAttribute("finish", SprintHelper.finishSprint(sprint));
            return "sprint/sprint";
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return "403";
    }



    /**
     * Servlet for delete sprint by id sprint and id project sprint
     * */
    @RequestMapping(value = "/manager/deleteSprint", method = RequestMethod.GET)
    public String deleteSprint(Integer idSprint, Integer idProject) {
        try {
            Sprint sprint = service.getByPK(idSprint);
            for (Journal j : journalService.getJournalsByNameParam("idSprint", idSprint)) {
                journalService.delete(j);
            }
            for (Task task : sprint.getTasks()) {
                taskService.delete(task);
            }
            service.delete(sprint);
            service.commit();
            return "redirect:/idProject?id=" + idProject;
        } catch (PersistException | SQLException e){
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        }
        return "404";
    }


    /**
     * Servlet for start sprint by id sprint
     * */
    @RequestMapping(value = "/manager/startSprint", method = RequestMethod.GET)
    public String start(@RequestParam Integer idSprint) {
        try {
            Sprint sprint = service.getByPK(idSprint);
            sprint.setStarted(true);
            service.update(sprint);
            service.commit();
            return "redirect:/idSprint?id=" + idSprint;
        } catch (PersistException | SQLException e){
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        }
        return "403";
    }

    @RequestMapping(value = "/manager/finishSprint", method = RequestMethod.GET)
    public String finish(@RequestParam Integer idSprint) {
        try {
            Sprint sprint = service.getByPK(idSprint);
            sprint.setFinished(true);
            service.update(sprint);
            service.commit();
            return "redirect:/idSprint?id=" + idSprint;
        } catch (PersistException | SQLException e){
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
