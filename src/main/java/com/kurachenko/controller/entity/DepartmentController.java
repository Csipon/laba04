package com.kurachenko.controller.entity;

import com.kurachenko.entity.Department;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

/**
 * @author Pavel Kurachenko
 * @since 1/13/2017
 */
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService service;


    @RequestMapping(value = "/getAllDepartment", method = RequestMethod.GET)
    public String getAll(Model model){
        try {
            model.addAttribute("deptList", service.getAll());
        }catch (PersistException e) {
            e.printStackTrace();
        }
        return "admin/department/departments";
    }



    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
    public String addDepartment(Department department, Errors errors)
    {
        if (errors.hasErrors()) {
            return "admin/register/register";
        }
        try {
            Department temp = service.create();
            temp.setName(department.getName());
            temp.setDescription(department.getDescription());
            temp.setNumber(department.getNumber());
            service.update(temp);
            service.commit();
            return "redirect:/admin";
        }  catch (PersistException | SQLException e) {
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        }
        return "404";
    }


    @RequestMapping(value = "/idDepartment", method = RequestMethod.GET)
    public String information(@RequestParam Integer id, Model model){
        try{
            model.addAttribute("department", service.getByPK(id));
        }catch (PersistException e){
            e.getMessage();
        }
        return "department/department";
    }


    @RequestMapping(value = "/deleteDepartment", method = RequestMethod.GET)
    public String delete(Integer id, Boolean confirm){
        try{
            Department department = service.getByPK(id);
            if (department != null){
                if (confirm){
                    service.delete(department);
                    service.commit();
                    return "redirect:/getAllDepartment";
                }
            }
        } catch (PersistException | SQLException e) {
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        }
        return "404";
    }

    @Transactional
    @RequestMapping(value = "/createDepartment", method = RequestMethod.GET)
    public String newDepartment(){
        return "admin/register/department";
    }
}
