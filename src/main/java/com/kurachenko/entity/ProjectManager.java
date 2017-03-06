package com.kurachenko.entity;

import com.kurachenko.exception.PersistException;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * this project manager entity, this object isn't passive, he's need for manage his projects and take tasks for
 * own subordinates, this entity extend simple employee and have a more scope than simple employee
 * @author Pavel Kurachenko
 * @since 12/17/2016
 */
public class ProjectManager extends Employee {
    private Project[] managedProjects;
    private Employee[] subordinates;
    private Double budget;


    public ProjectManager() {
    }

    public ProjectManager(ProjectManager manager) {
        setId(manager.getId());
        setFirstName(manager.getName());
        setLastName(manager.getSurname());
        setLogin(manager.getLogin());
        setPassword(manager.getPassword());
        setDescription(manager.getDescription());
        setLevelQualification(manager.getLevelQualification());
        setHiredate(manager.getHiredate());
        try {
            setProjects(manager.getProjects());
            setDepartment(manager.getDepartment());
            this.managedProjects = manager.getManagedProjects();
            this.subordinates = manager.getSubordinates();
            this.budget = manager.getBudget();
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    public Project[] getManagedProjects() throws PersistException {
        return managedProjects;
    }

    public void setManagedProjects(Project[] managedProjects) {
        this.managedProjects = managedProjects;
    }

    public Employee[] getSubordinates() throws PersistException {
        return subordinates;
    }

    public void setSubordinates(Employee[] subordinates) {
        this.subordinates = subordinates;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}
