package com.kurachenko.controller.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
     * Mapper for convert object in JSON format
     * */
    private ObjectMapper mapper = new ObjectMapper();


    /**
     * Servlet for prepare to create task
     * */
    @RequestMapping(value = "/manager/toCreateTask", method = RequestMethod.GET)
    public String prepare(Model model, Integer idSprint) {
        model.addAttribute("sprint", idSprint);
        model.addAttribute("qualifications", Qualification.values());
        return "manager/create/task";
    }


    /**
     * Servlet for add new task in sprint
     * @param idSprint this id sprint in which need add new task
     * @param qualification this is level qualification for task
     * */
    @RequestMapping(value = "/manager/addTask", method = RequestMethod.POST)
    public void create(HttpServletResponse response, String name, String description, Integer estimate, Integer idSprint
            , String qualification, String tasks) {
        try {
            Task temp = service.create();
            temp.setName(name);
            temp.setDescription(description);
            temp.setLevelQualification(Qualification.valueOf(qualification));
            temp.setEstimate(estimate);
            System.out.println(tasks);
            if (tasks != null && tasks.length() > 0) {
                List<Task> taskList = new ArrayList<>();
                for (Integer id : mapper.readValue(tasks, Integer[].class)){
                    taskList.add(service.getByPK(id));
                }
                temp.setTasks(taskList.toArray(new Task[taskList.size()]));
            }
            service.update(temp);
            Sprint sprint = sprintService.getByPK(idSprint);
            TaskHelper.setTaskInSprint(sprint, temp);
            sprintService.update(sprint);
            sprintService.commit();
            service.commit();
            response.setCharacterEncoding("UTF-8");
        } catch (PersistException | SQLException | IOException e) {
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }


    /**
     * Servlet for get task by id and sprint id
     * @param id this id task
     * @param idSprint this id sprint which have this task
     **/
    @RequestMapping(value = "/maker/idTask", method = RequestMethod.GET)
    public String getByID(@RequestParam Integer id, Integer idSprint, Model model) {
        try {
            Task task = service.getByPK(id);
            Sprint sprint = sprintService.getByPK(idSprint);
            model.addAttribute("task", task);
            model.addAttribute("start", TaskHelper.startTask(task));
            model.addAttribute("idSprint", sprint.getId());
            model.addAttribute("sprintStarted", sprint.isStarted());
            return "task/task";
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return "403";
    }



    /**
     * Servlet for start task
     * */
    @RequestMapping(value = "/employee/startTask", method = RequestMethod.GET)
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
    @RequestMapping(value = "/employee/finishTask", method = RequestMethod.GET)
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
    @RequestMapping(value = "/manager/deleteTask", method = RequestMethod.GET)
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
