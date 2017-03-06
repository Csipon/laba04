package com.kurachenko.service.daoabstract;

import com.kurachenko.entity.Customer;
import com.kurachenko.entity.Project;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import com.kurachenko.service.daoimpl.ProjectService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 1/31/2017
 */
public abstract class CustomerAbstract extends AbstractJDBCDao<Customer, Integer> {
    public CustomerAbstract(DaoFactory parentFactory, Connection connection) throws PersistException {
        super(parentFactory, connection);
    }

    public List<Project> getCustomerProject(Integer id) throws PersistException {
        List<Project> list = new ArrayList<>();
        ProjectService service = (ProjectService) getService(Project.class);
        for (Integer idProject : getListId("customer", id)) {
            list.add(service.getByPK(idProject));
        }
        return list;
    }


}
