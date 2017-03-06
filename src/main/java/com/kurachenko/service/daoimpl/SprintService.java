package com.kurachenko.service.daoimpl;

import com.kurachenko.entity.Sprint;
import com.kurachenko.exception.PersistException;
import com.kurachenko.proxy.SprintProxy;
import com.kurachenko.service.daoabstract.AbstractJDBCDao;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

/**
 * @author Pavel Kurachenko
 * @since 1/22/2017
 */
@Service
public class SprintService extends AbstractJDBCDao<Sprint, Integer> {

    @Autowired
    public SprintService(DaoFactory<Connection> parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }

    @Override
    public Sprint create() throws PersistException {
        return persist(createNewObject());
    }

    @Override
    public Sprint createNewObject() {
        return new SprintProxy(this);
    }
}
