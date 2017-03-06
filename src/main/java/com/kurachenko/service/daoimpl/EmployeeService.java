package com.kurachenko.service.daoimpl;

import com.kurachenko.entity.Employee;
import com.kurachenko.exception.PersistException;
import com.kurachenko.proxy.EmployeeProxy;
import com.kurachenko.service.daoabstract.AbstractJDBCDao;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

/**
 * @author Pavel Kurachenko
 * @since 12/22/2016
 */
@Service
public class EmployeeService extends AbstractJDBCDao<Employee, Integer> {

    @Autowired
    public EmployeeService(DaoFactory<Connection> parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }

    @Override
    public Employee create() throws PersistException {
        return persist(createNewObject());
    }

    @Override
    public Employee createNewObject() {
        return new EmployeeProxy(this);
    }
}
