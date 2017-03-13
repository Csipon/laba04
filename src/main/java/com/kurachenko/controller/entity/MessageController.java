package com.kurachenko.controller.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurachenko.entity.Employee;
import com.kurachenko.entity.Message;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.MessageService;
import com.kurachenko.service.daoimpl.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Controller which handle all request which bound with message
 * @author Pavel Kurachenko
 * @since 2/7/2017
 */
@Controller
public class MessageController {
    /**
     * Mapper for convert object in JSON format
     * */
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MessageService service;
    @Autowired
    private TaskService taskService;

    /**
     * servlet for send message
     * @param message this our filled entity
     * */
    @RequestMapping(value = "/maker/sendMessage", method = RequestMethod.POST)
    public void sendMessage(Message message, HttpServletResponse response){
        try {
            Message temp = service.create();
            temp.setIdSender(message.getIdSender());
            temp.setDescription(message.getDescription());
            temp.setMessageProject(message.getMessageProject());
            temp.setMessageSprint(message.getMessageSprint());
            temp.setMessageTask(message.getMessageTask());
            temp.setSent(new Date());
            temp.setText(message.getText());
            service.update(temp);
            service.commit();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Message is sent");
        } catch (PersistException | SQLException | IOException e) {
            try {
                service.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }


    /**
     * servlet for get list messages by param name and id param
     * @param paramName this name param by which we will be getting result
     * @param id this id param
     * */
    @RequestMapping(value = "/maker/getMessage", method = RequestMethod.GET)
    public void getMessage(String paramName, Integer id, HttpServletResponse response){
        try {
            Map<String, Map<String, String>> map = new TreeMap<>(comparator);
            for (Message msg : service.getListMessage(paramName, id)){
                Employee employee = service.getSender(msg.getIdSender());
                Map<String, String> info = new HashMap<>();
                info.put(employee.getName() + " " + employee.getSurname() +
                        ", task - " + taskService.getByPK(msg.getMessageTask()).getName(), msg.getText());
                map.put(String.valueOf(msg.getSent().getTime()), info);
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(mapper.writeValueAsString(map));
        } catch (PersistException | IOException e) {
            e.printStackTrace();
        }

    }



    /**
     * Comparator which equal two Dates in numeric format represented as string, order by biggest number is first
     * */
    private Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return Long.parseLong(o2) > Long.parseLong(o1) ? 1 : Long.parseLong(o2) < Long.parseLong(o1) ? -1 : 0;
        }
    };
}
