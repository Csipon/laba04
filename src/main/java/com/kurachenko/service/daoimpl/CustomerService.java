package com.kurachenko.service.daoimpl;

import com.kurachenko.entity.Customer;
import com.kurachenko.exception.PersistException;
import com.kurachenko.proxy.CustomerProxy;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import com.kurachenko.service.daoabstract.CustomerAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

/**
 * @author Pavel Kurachenko
 * @since 1/4/2017
 */
@Service
public class CustomerService extends CustomerAbstract {

    @Autowired
    public CustomerService(DaoFactory<Connection> parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }

    @Override
    public Customer create() throws PersistException {
        return persist(createNewObject());
    }

    @Override
    public Customer createNewObject() {
        return new CustomerProxy(this);
    }
}