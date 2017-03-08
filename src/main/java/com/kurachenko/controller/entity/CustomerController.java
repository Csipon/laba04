package com.kurachenko.controller.entity;

import com.kurachenko.entity.Customer;
import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
     * @param request need for get customer id and current session
     * */
    @RequestMapping(value = "customer/profileCustomer", method = RequestMethod.GET)
    public String profileCustomer(HttpServletRequest request){
        HttpSession session = request.getSession();
        Identified object = (Identified) session.getAttribute("user");
        try {
            session.setAttribute("projects", service.getCustomerProject((Integer) object.getId()));
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
     * @param customer filled customer from form
     * @param model need for set successful message
     * */
    @RequestMapping(value = "/admin/addCustomer", method = RequestMethod.POST)
    public String addCustomer(Customer customer, Model model) {
        try {
            if (service.validLogin(customer.getLogin())) {
                Customer temp = service.create();
                temp.setName(customer.getName());
                temp.setSurname(customer.getSurname());
                temp.setCompanyName(customer.getCompanyName());
                temp.setDescription(customer.getDescription());
                temp.setLogin(customer.getLogin());
                temp.setPassword(customer.getPassword());
                service.update(temp);
                model.addAttribute("message", "Customer is successful created");
                service.commit();
                return "redirect:/admin";
            }
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
    @RequestMapping(value = "/idCustomer", method = RequestMethod.GET)
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
    @RequestMapping(value = "/deleteCustomer", method = RequestMethod.GET)
    public String delete(@RequestParam Integer id, @RequestParam String password){

        try{
            Customer customer = service.getByPK(id);
            if (customer != null){
                if (customer.getPassword().equals(password)){
                    service.delete(customer);
                    service.commit();
                    return "redirect:/getAllCustomer";
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
}
