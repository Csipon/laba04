package com.kurachenko.controller;

import com.kurachenko.entity.Project;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.daohelpers.StatisticHelper;
import com.kurachenko.service.daoimpl.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * controller for get statistic by project, for customer
 * @author Pavel Kurachenko
 * @since 1/30/2017
 */
@Controller
public class StatisticController {

    @Autowired
    private ProjectService projectService;


    /**
     * servlet which make a statistic by project
     * @param idProject this id project by which need make statistic
     * @return if all ok go to project page, else go to error page
     * */
    @RequestMapping(value = "/customer/loadStatistic", method = RequestMethod.GET)
    public String loadStatistic(Integer idProject, Model model){
        try {
            Project project = projectService.getByPK(idProject);
            Map<Integer, Double> statistic = StatisticHelper.getStatisticProject(project);
            model.addAttribute("statistic", statistic);
            model.addAttribute("project", project);
            return "project/project";
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return "404";
    }
}
