package com.kurachenko.controller.entity;

import com.kurachenko.entity.Journal;
import com.kurachenko.entity.Sprint;
import com.kurachenko.entity.Task;
import com.kurachenko.entity.enums.Qualification;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.daohelpers.TaskHelper;
import com.kurachenko.service.daoimpl.JournalService;
import com.kurachenko.service.daoimpl.SprintService;
import com.kurachenko.service.daoimpl.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

/**
 * Controller which handle all request which bound with task
 * @author Pavel Kurachenko
 * @since 1/24/2017
 */
@Controller
public class TaskController {

    @Autowired
    private SprintService sprintService;
    @Autowired
    private TaskService service;
    @Autowired
    private JournalService journalService;


    /**
     * Servlet for prepare to create task
     * */
    @RequestMapping(value = "/toCreateTask", method = RequestMethod.GET)
    public String prepare(Model model, Integer idSprint, Integer idProject) {
        model.addAttribute("sprint", idSprint);
        model.addAttribute("project", idProject);
        model.addAttribute("qualifications", Qualification.values());
        return "manager/create/task";
    }


    /**
     * Servlet for add new task in sprint
     * @param task this entity which we filled in form
     * @param idSprint this id sprint in which need add new task
     * @param idProject this id project in which has sprint in which we added new task
     *                  need for redirect after created and update
     * @param qualification this is level qualification for task
     * */
    @RequestMapping(value = "addTask", method = RequestMethod.POST)
    public String create(Task task, Integer idSprint, Integer idProject, String qualification) {
        try {
            Task temp = service.create();
            temp.setName(task.getName());
            temp.setDescription(task.getDescription());
            temp.setLevelQualification(Qualification.valueOf(qualification));
            temp.setEstimate(task.getEstimate());
            service.update(temp);
            Sprint sprint = sprintService.getByPK(idSprint);
            TaskHelper.setTaskInSprint(sprint, temp);
            sprintService.update(sprint);
            service.commit();
            return "redirect:/idSprint?id=" + idSprint + "&idProject=" + idProject;
        } catch (PersistException | SQLException e) {
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return "403";
    }


    /**
     * Servlet for get task by id and sprint id
     * @param id this id task
     * @param idSprint this id sprint which have this task
     **/
    @RequestMapping(value = "/idTask", method = RequestMethod.GET)
    public String getByID(@RequestParam Integer id, Integer idSprint, Model model) {
        try {
            Task task = service.getByPK(id);
            model.addAttribute("task", task);
            model.addAttribute("start", TaskHelper.startTask(task));
            model.addAttribute("idSprint", idSprint);
            return "task/task";
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return "403";
    }



    /**
     * Servlet for start task
     * */
    @RequestMapping(value = "/startTask", method = RequestMethod.GET)
    public String start(@RequestParam Integer idTask, Integer idSprint) {
        try {
            Task task = service.getByPK(idTask);
            task.setStarted(true);
            service.update(task);
            service.commit();
            return "redirect:/idTask?id=" + idTask + "&idSprint=" + idSprint;
        } catch (PersistException | SQLException e) {
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return "403";
    }



    /**
     * Servlet for finish task
     * */
    @RequestMapping(value = "/finishTask", method = RequestMethod.GET)
    public String finish(@RequestParam Integer idTask, Integer idSprint) {
        try {
            Task task = service.getByPK(idTask);
            task.setCompleted(true);
            service.update(task);
            service.commit();
            return "redirect:/idTask?id=" + idTask + "&idSprint=" + idSprint;
        }catch (PersistException | SQLException e) {
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return "403";
    }



    /**
     * Servlet for delete task by id
     * @param idTask this id task which need delete
     * @param idSprint this id sprint which have task which need delete
     * @param idProject need for redirect in need sprint after delete task
     * */
    @RequestMapping(value = "/deleteTask", method = RequestMethod.GET)
    public String deleteTask(Integer idTask, Integer idSprint, Integer idProject) {
        try {
            for (Journal j : journalService.getJournalsByNameParam("task", idTask)) {
                journalService.delete(j);
            }
            service.delete(service.getByPK(idTask));
            service.commit();
            return "redirect:/idSprint?id=" + idSprint + "&idProject=" + idProject;
        }catch (PersistException | SQLException e) {
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return "404";
    }
}
