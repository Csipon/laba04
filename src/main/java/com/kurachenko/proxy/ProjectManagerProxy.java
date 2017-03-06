package com.kurachenko.proxy;

import com.kurachenko.entity.*;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.ProjectManagerService;

/**
 * @author Pavel Kurachenko
 * @since 12/30/2016
 */
public class ProjectManagerProxy extends ProjectManager {
    private ProjectManagerService service;

    public ProjectManagerProxy(ProjectManagerService service) {
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

    @Override
    public Project[] getManagedProjects() throws PersistException {
        if (super.getManagedProjects() == null) {
            setManagedProjects((Project[]) service.getArrays(this, "managedProjects"));
        }
        return super.getManagedProjects();
    }

    @Override
    public Employee[] getSubordinates() throws PersistException {
        if (super.getSubordinates() == null) {
            setSubordinates((Employee[]) service.getArrays(this, "subordinates"));
        }
        return super.getSubordinates();
    }
}
