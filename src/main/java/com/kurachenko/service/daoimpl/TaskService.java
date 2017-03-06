package com.kurachenko.service.daoimpl;

import com.kurachenko.entity.Task;
import com.kurachenko.exception.PersistException;
import com.kurachenko.proxy.TaskProxy;
import com.kurachenko.service.daoabstract.AbstractJDBCDao;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

/**
 * @author Pavel Kurachenko
 * @since 1/21/2017
 */
@Service
public class TaskService extends AbstractJDBCDao<Task, Integer> {

    @Autowired
    public TaskService(DaoFactory<Connection> parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }

    @Override
    public Task create() throws PersistException {
        return persist(createNewObject());
    }

    @Override
    public Task createNewObject() {
        return new TaskProxy(this);
    }
}
