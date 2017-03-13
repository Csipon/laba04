package com.kurachenko.controller.entity;

import com.kurachenko.entity.Customer;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Controller which handle all request which bound with customer
 * @author Pavel Kurachenko
 * @since 1/13/2017
 */
@Controller
public class CustomerController {
    @Autowired
    private CustomerService service;

    /**
     * Servlet which get id customer and load all customer projects after go on customer main page
     * */
    @RequestMapping(value = "/customer/profileCustomer", method = RequestMethod.GET)
    public String profileCustomer(Model model){
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            model.addAttribute("projects", service.getCustomerProject(customer.getId()));
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return "customer/main";
    }


    /**
     * Servlet for get all customer as list
     * */
    @RequestMapping(value = "/admin/getAllCustomer", method = RequestMethod.GET)
    public String getAll(Model model){
        try {
            model.addAttribute("customerList", service.getAll());
        }catch (PersistException e) {
            e.printStackTrace();
        }
        return "admin/customer/customers";
    }


    /**
     * Servlet for register new customer, if all good go on admin main page, else go on error page 403
     * @param model need for set successful message
     * */
    @RequestMapping(value = "/admin/addCustomer", method = RequestMethod.POST)
    public String addCustomer(String name, String surname, String companyName, String description, String password, String login, Model model) {
        try {
            Customer temp = service.create();
            temp.setName(name);
            temp.setSurname(surname);
            temp.setCompanyName(companyName);
            temp.setDescription(description);
            temp.setLogin(login);
            temp.setPassword(password);
            service.update(temp);
            model.addAttribute("message", "Customer is successful created");
            service.commit();
            return "redirect:/admin/profileAdmin";
        } catch (PersistException | SQLException e) {
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
     * Servlet for get customer ny id
     * @param id request id
     * */
    @RequestMapping(value = "/maker/idCustomer", method = RequestMethod.GET)
    public String information(@RequestParam Integer id, Model model){

        try{
            model.addAttribute("customer", service.getByPK(id));
        }catch (PersistException e){
            e.getMessage();
        }
        return "customer/customer";
    }



    /**
     * Servlet for delete customer
     * @param id id needed customer
     * @param password password this customer for check
     * */
    @RequestMapping(value = "/admin/deleteCustomer", method = RequestMethod.POST)
    public void delete(HttpServletResponse response, @RequestParam Integer id, @RequestParam String password){
        try{
            Customer customer = service.getByPK(id);
            if (customer != null){
                if (customer.getPassword().equals(password)){
                    service.delete(customer);
                    service.commit();
                }
            }
            response.setCharacterEncoding("UTF-8");
        } catch (PersistException | SQLException e) {
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.getMessage();
        }
    }
}
