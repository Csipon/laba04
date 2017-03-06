package com.kurachenko.service.daoimpl;

import com.kurachenko.entity.Journal;
import com.kurachenko.exception.PersistException;
import com.kurachenko.proxy.JournalProxy;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import com.kurachenko.service.daoabstract.JournalAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

/**
 * @author Pavel Kurachenko
 * @since 1/25/2017
 */
@Service
public class JournalService extends JournalAbstract {

    @Autowired
    public JournalService(DaoFactory<Connection> parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }

    @Override
    public Journal create() throws PersistException {
        return persist(createNewObject());
    }

    @Override
    public Journal createNewObject() {
        return new JournalProxy(this);
    }
}
