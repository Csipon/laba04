package com.kurachenko.service.daoimpl;

import com.kurachenko.entity.Administrator;
import com.kurachenko.exception.PersistException;
import com.kurachenko.proxy.AdministratorProxy;
import com.kurachenko.service.daoabstract.AbstractJDBCDao;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

/**
 * @author Pavel Kurachenko
 * @since 1/19/2017
 */
@Service
public class AdministratorService extends AbstractJDBCDao<Administrator, Integer> {

    @Autowired
    public AdministratorService(DaoFactory<Connection> parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }

    @Override
    public Administrator create() throws PersistException {
        return persist(createNewObject());
    }

    @Override
    public Administrator createNewObject() {
        return new AdministratorProxy(this);
    }
}
