package com.kurachenko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Pavel Kurachenko
 * @since 1/13/2017
 */

@Controller
public class ErrorController {
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String errorNotFound() {
        return "404";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String errorRegister() {
        return "403";
    }
}
