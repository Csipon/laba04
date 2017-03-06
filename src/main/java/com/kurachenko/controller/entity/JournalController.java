package com.kurachenko.controller.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurachenko.entity.Journal;
import com.kurachenko.entity.Project;
import com.kurachenko.entity.ProjectManager;
import com.kurachenko.entity.Sprint;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.JournalService;
import com.kurachenko.service.daoimpl.ProjectService;
import com.kurachenko.service.daoimpl.SprintService;
import com.kurachenko.service.daoimpl.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller which handle all request which bound with journal
 * @author Pavel Kurachenko
 * @since 1/25/2017
 */
@Controller
public class JournalController {

    @Autowired
    private JournalService service;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private SprintService sprintService;
    @Autowired
    private TaskService taskService;



    private ObjectMapper mapper = new ObjectMapper();


    /**
     * Servlet for get {@code Sprint[]} from project and transport this array as string
     * in JSON format, if thrown exception then print stack trace
     * @param id this is id project from which need get {@code Sprint[]}
     * @param response need for write array as string in JSON format
     * */
    @RequestMapping(value = "/loadSprints", method = RequestMethod.GET)
    public void loadSprints(Integer id, HttpServletResponse response) {
        try {
            Project project = projectService.getByPK(id);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(mapper.writeValueAsString(project.getSprints()));
        } catch (PersistException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Servlet for get {@code Task[]} from sprint and transport this array as string
     * in JSON format, if thrown exception then print stack trace
     * @param id this is id project from which need get {@code Task[]}
     * @param response need for write array as string in JSON format
     * */
    @RequestMapping(value = "/loadTasks", method = RequestMethod.GET)
    public void loadTasks(Integer id, HttpServletResponse response) throws IOException {
        try {
            Sprint sprint = sprintService.getByPK(id);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(mapper.writeValueAsString(sprint.getTasks()));
        } catch (PersistException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Servlet for get {@code Employee[]} from project manager and transport this array as string
     * in JSON format, if thrown exception then print stack trace
     * @param request from request get a user and we know that this user ProjectManager, after
     *                we a get his subordinates as {@code Employee[]}
     * @param response need for write array as string in JSON format
     * */
    @RequestMapping(value = "/loadEmployees", method = RequestMethod.GET)
    public void loadEmployees(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            ProjectManager manager = (ProjectManager) session.getAttribute("user");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(mapper.writeValueAsString(manager.getSubordinates()));
        } catch (PersistException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Servlet for create new journal for task and add on this task employee which will be make her,
     * if PersistException or SQLException make a rollback and print message< if IOException -
     * print stack trace
     * @param idSprint sprint id need for journal, that journal knew about her sprint
     * @param idTask need for load task from DB and set task in journal
     * @param description this description for journal, note of manager
     * @param list this array id employee which need add in journal, but list transport as string
     *             in JSON format
     * */
    @RequestMapping(value = "/addJournal", method = RequestMethod.GET)
    public void addJournal(Integer idSprint, Integer idTask, String description, String list) {
        try {
            Journal temp = service.create();
            temp.setDescription(description);
            temp.setTask(taskService.getByPK(idTask));
            temp.setIdSprint(idSprint);
            temp.setMapEmployee(service.makeMap(Arrays.asList(mapper.readValue(list, Integer[].class))));
            service.update(temp);
            service.commit();
        } catch (PersistException | SQLException e) {
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Servlet for {@code Employee} which load his task and write this task in response, if thrown exception -
     * print stack trace
     * @param idSprint need for search journal with this sprint id
     * @param idEmployee need for check in journal map contains id this employee or no,
     *                   if yes then add journal in result list
     * @param response need fir write list of journal as string in JSON format
     * */
    @RequestMapping(value = "/loadEmployeeTasks", method = RequestMethod.GET)
    public void loadEmployeeTasks(Integer idSprint, Integer idEmployee, HttpServletResponse response) {
        try {
            List<Journal> allSprintJournals = service.getJournalsByNameParam("idSprint", idSprint);
            List<Journal> journals = new ArrayList<>();
            for (Journal j : allSprintJournals) {
                if (j.getMapEmployee().containsKey(idEmployee)) {
                    journals.add(j);
                }
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(mapper.writeValueAsString(journals));
        } catch (PersistException | IOException e) {
            e.printStackTrace();
        }
    }

    /** Servlet need for load list journals by param name and id for ({@code Employee})
     * , if thrown exception just print stack trace
     * @param paramName name of parameter with help which will be search need journals
     * @param id this id of parameter with help which will be search need journals
     * @param idEmployee this employee for which we will be search journals
     * @param response need for write list journals as string in JSON format
     * */
    @RequestMapping(value = "/loadJournals", method = RequestMethod.GET)
    public void loadJournals(String paramName, Integer id, Integer idEmployee, HttpServletResponse response){
        try {
            List<Journal> journals = service.getListEmployee(service.getJournalsByNameParam(paramName, id), idEmployee);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(mapper.writeValueAsString(journals));
        } catch (PersistException | IOException e) {
            e.printStackTrace();
        }
    }

    /** Servlet need for load list journals by param name and id, if thrown exception just print stack trace
     * @param paramName name of parameter with help which will be search need journals
     * @param id this id of parameter with help which will be search need journals
     * @param response need for write list journals as string in JSON format
     * */
    @RequestMapping(value = "/loadSprintJournals", method = RequestMethod.GET)
    public void loadSprintJournals(String paramName, Integer id, HttpServletResponse response){
        try {
            List<Journal> journals = service.getJournalsByNameParam(paramName, id);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(mapper.writeValueAsString(journals));
        } catch (PersistException | IOException e) {
            e.printStackTrace();
        }
    }
}
