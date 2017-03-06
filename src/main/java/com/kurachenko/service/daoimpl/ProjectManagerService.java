package com.kurachenko.service.daoimpl;

import com.kurachenko.entity.ProjectManager;
import com.kurachenko.exception.PersistException;
import com.kurachenko.proxy.ProjectManagerProxy;
import com.kurachenko.service.daoabstract.AbstractJDBCDao;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

/**
 * @author Pavel Kurachenko
 * @since 12/26/2016
 */
@Service
public class ProjectManagerService extends AbstractJDBCDao<ProjectManager, Integer> {

    @Autowired
    public ProjectManagerService(DaoFactory<Connection> parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }

    @Override
    public ProjectManager create() throws PersistException {
        return persist(createNewObject());
    }

    @Override
    public ProjectManager createNewObject() {
        return new ProjectManagerProxy(this);
    }
}
