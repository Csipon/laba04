package com.kurachenko.proxy;

import com.kurachenko.entity.Department;
import com.kurachenko.entity.Employee;
import com.kurachenko.entity.Project;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.EmployeeService;

/**
 * @author Pavel Kurachenko
 * @since 12/30/2016
 */
public class EmployeeProxy extends Employee {
    private EmployeeService service;

    public EmployeeProxy(EmployeeService service) {
        this.service = service;
    }


    @Override
    public Department getDepartment() throws PersistException {
        if (super.getDepartment() == null) {
            setDepartment((Department) service.getObject(this, Department.class));
        }
        return super.getDepartment();
    }

    @Override
    public Project[] getProjects() throws PersistException {
        if (super.getProjects() == null) {
            setProjects((Project[]) service.getArrays(this, "projects"));
        }
        return super.getProjects();
    }
}
