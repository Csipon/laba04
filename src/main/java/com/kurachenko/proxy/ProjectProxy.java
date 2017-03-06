package com.kurachenko.proxy;

import com.kurachenko.entity.Customer;
import com.kurachenko.entity.Project;
import com.kurachenko.entity.Sprint;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.ProjectService;

/**
 * @author Pavel Kurachenko
 * @since 1/4/2017
 */
public class ProjectProxy extends Project {

    private ProjectService service;

    public ProjectProxy(ProjectService service) {
        this.service = service;
    }

    @Override
    public Customer getCustomer() throws PersistException {
        if (super.getCustomer() == null) {
            setCustomer((Customer) service.getObject(this, Customer.class));
        }
        return super.getCustomer();
    }


    @Override
    public Sprint[] getSprints() throws PersistException {
        if (super.getSprints() == null){
            setSprints((Sprint[]) service.getArrays(this, "sprints"));
        }
        return super.getSprints();
    }
}
