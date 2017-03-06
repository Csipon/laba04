package com.kurachenko.service.daoimpl;

import com.kurachenko.entity.Department;
import com.kurachenko.exception.PersistException;
import com.kurachenko.proxy.DepartmentProxy;
import com.kurachenko.service.daoabstract.AbstractJDBCDao;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

/**
 * @author Pavel Kurachenko
 * @since 12/29/2016
 */
@Service
public class DepartmentService extends AbstractJDBCDao<Department, Integer> {

    @Autowired
    public DepartmentService(DaoFactory<Connection> parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }

    @Override
    public Department create() throws PersistException {
        return persist(createNewObject());
    }

    @Override
    public Department createNewObject() {
        return new DepartmentProxy(this);
    }
}
