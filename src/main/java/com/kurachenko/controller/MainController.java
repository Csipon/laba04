package com.kurachenko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Pavel Kurachenko
 * @since 1/13/2017
 */

@Controller
public class MainController {
    @RequestMapping(value = "/tologin", method = RequestMethod.GET)
    public String login(){
        return "/login";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "../../index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminMain()  {
        return "admin/main";
    }


    @RequestMapping(value = "/journal", method = RequestMethod.GET)
    public String journal()  {
        return "manager/journal";
    }

    @RequestMapping(value = "/profileMgr", method = RequestMethod.GET)
    public String profile(){
        return "manager/main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }

        model.setViewName("manager/main");

        return model;
    }
}
