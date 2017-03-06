package com.kurachenko.service.daoabstract;

import com.kurachenko.entity.Employee;
import com.kurachenko.entity.Message;
import com.kurachenko.entity.ProjectManager;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import com.kurachenko.service.daoimpl.EmployeeService;
import com.kurachenko.service.daoimpl.MessageService;
import com.kurachenko.service.daoimpl.ProjectManagerService;
import com.kurachenko.service.daoimpl.StoreConstantForDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 2/9/2017
 */
public abstract class MessageAbstract extends AbstractJDBCDao<Message, Integer> {
    public MessageAbstract(DaoFactory<Connection> parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }



    public List<Message> getListMessage(String paramName, Integer id) throws PersistException {
        List<Message> messages = new ArrayList<>();
        MessageService service = (MessageService) getService(Message.class);
        for (Integer i : getListId(paramName, id)) {
            messages.add(service.getByPK(i));
        }
        return messages;
    }

    public Employee getSender(Integer id) throws PersistException {
        Employee employee;
        try(PreparedStatement statement = getConnection().prepareStatement(StoreConstantForDB.SELECT_OBJECT_BY_ID)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                if (rs.getString("name").equalsIgnoreCase(Employee.class.getSimpleName())){
                    employee = ((EmployeeService) getService(Employee.class)).getByPK(id);
                }else if (rs.getString("name").equalsIgnoreCase(ProjectManager.class.getSimpleName())){
                    employee = ((ProjectManagerService) getService(ProjectManager.class)).getByPK(id);
                }else {
                    throw new PersistException();
                }
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
