package com.kurachenko.service.daoimpl;

import com.kurachenko.entity.Project;
import com.kurachenko.exception.PersistException;
import com.kurachenko.proxy.ProjectProxy;
import com.kurachenko.service.daoabstract.AbstractJDBCDao;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

/**
 * @author Pavel Kurachenko
 * @since 12/28/2016
 */
@Service
public class ProjectService extends AbstractJDBCDao<Project, Integer> {
    @Autowired
    public ProjectService(DaoFactory<Connection> parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }

    @Override
    public Project create() throws PersistException {
        return persist(createNewObject());
    }

    @Override
    public Project createNewObject() {
        return new ProjectProxy(this);
    }
}
