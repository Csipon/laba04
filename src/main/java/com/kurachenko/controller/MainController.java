package com.kurachenko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
