package com.kurachenko.controller.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller for logout
 * @author Pavel Kurachenko
 * @since 1/19/2017
 */

@Controller
public class ExitController{


    /**
     * Servlet for logout
     * */
    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(HttpServletRequest req) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        req.setAttribute("msg", "You've been logged out successfully.");
        return "/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
