package com.kurachenko.controller.entity;

import com.kurachenko.entity.ProjectManager;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Controller which handle all request which bound with manager
 * @author Pavel Kurachenko
 * @since 1/17/2017
 */
@Controller
public class ManagerController {

    @Autowired
    private ProjectManagerService service;

    @RequestMapping(value = "/manager/profileMgr", method = RequestMethod.GET)
    public String profile(){
        return "manager/main";
    }


    /**
     * Servlet for get manager by id
     * */
    @RequestMapping(value = "/maker/idManager", method = RequestMethod.GET)
    public String information(@RequestParam Integer id, Model model){
        try{
            model.addAttribute("manager", service.getByPK(id));
        }catch (PersistException e){
            e.getMessage();
        }
        return "manager/manager";
    }


    /**
     * Servlet for delete manager by id and manager password
     * @param id identified manager
     * @param password password manager, need for confirm
     * */
    @RequestMapping(value = "/admin/deleteManager", method = RequestMethod.POST)
    public void delete(HttpServletResponse response, @RequestParam Integer id, @RequestParam String password){
        try{
            ProjectManager manager = service.getByPK(id);
            if (manager != null){
                if (manager.getPassword().equals(password)){
                    service.delete(manager);
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
}
