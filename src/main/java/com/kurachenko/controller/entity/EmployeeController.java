package com.kurachenko.controller.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurachenko.entity.Employee;
import com.kurachenko.entity.Journal;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.daohelpers.CheckHelper;
import com.kurachenko.service.daoimpl.EmployeeService;
import com.kurachenko.service.daoimpl.JournalService;
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
import java.util.Date;
import java.util.List;

/**
 * Controller which handle all request which bound with employee
 * @author Pavel Kurachenko
 * @since 1/13/2017
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @Autowired
    private JournalService journalService;

    /**
     * Mapper for convert object in JSON format
     * */
    private ObjectMapper mapper = new ObjectMapper();


    /**
     * get all employee as list
     * */
    @RequestMapping(value = "/admin/getAllEmployee", method = RequestMethod.GET)
    public String getAll(Model model){
        try {
            model.addAttribute("empList", service.getAll());
        }catch (PersistException e) {
            e.printStackTrace();
        }
        return "admin/employee/employees";
    }



    @RequestMapping(value = "/employee/profileEmp", method = RequestMethod.GET)
    public String profile(){
        return "employee/main";
    }



    /**
     * Get employee by id and go on employee page
     * */
    @RequestMapping(value = "/idEmp", method = RequestMethod.GET)
    public String information(Integer id, Model model){
        try{
            model.addAttribute("employee", service.getByPK(id));
        }catch (PersistException e){
            e.getMessage();
        }
        return "employee/employee";
    }


    /**
     * Delete employee by id
     * @param id id employee which need delete
     * @param password password employee for check
     * */
    @RequestMapping(value = "/admin/deleteEmployee", method = RequestMethod.POST)
    public void delete(HttpServletResponse response, Integer id, String password){
        try{
            Employee employee = service.getByPK(id);
            if (employee != null){
                if (employee.getPassword().equals(password)){
                    service.delete(employee);
                    service.commit();
                }
            }
            response.setCharacterEncoding("UTF-8");
        }catch (PersistException | SQLException e){
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        }
    }


    /**
     * Servlet for accept task, if all good redirect in accepted task, else go on error page 404
     * @param idEmployee employee which accept task
     * @param idJournal journal with current task and need for write that this employee accept task
     * @param model need for set successful text
     * */
    @RequestMapping(value = "/employee/acceptTask", method = RequestMethod.GET)
    public String acceptTask(@RequestParam Integer idJournal, Integer idEmployee, Model model){

        try{
            Journal journal = journalService.getByPK(idJournal);
            journal.getMapEmployee().put(idEmployee, true);
            if (CheckHelper.checkAccept(journal.getMapEmployee())){
                journal.setStartTask(new Date());
            }
            journalService.update(journal);
            model.addAttribute("accept", "You are successful accept this task");
            service.commit();
            return "redirect:/idTask?id=" + journal.getTask().getId() + "&idSprint=" + journal.getIdSprint();
        }catch (PersistException | SQLException e){
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
     * Servlet for show employee which make current task
     * @param idJournal journal in which store makers
     * @param response need for write in JSON format response
     * */
    @RequestMapping(value = "/maker/showMakers", method = RequestMethod.GET)
    public void showMakers(Integer idJournal, HttpServletResponse response) {
        try {
            Journal journal = journalService.getByPK(idJournal);
            List<Employee> list = getWorkersInTask(journal);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(mapper.writeValueAsString(list));
        } catch (PersistException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method take a object {@code Journal} and check map with id employees,
     * add all employees in list and return him
     * @param journal object from which we will take a map
     * @return List map as list where each object in list it is {@code Employee}
     * */
    private List<Employee> getWorkersInTask(Journal journal) throws PersistException {
        List<Employee> list = new ArrayList<>();
        for (Integer i : journal.getMapEmployee().keySet()){
            list.add(service.getByPK(i));
        }
        return list;
    }
}
