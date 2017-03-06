package com.kurachenko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurachenko.entity.enums.Qualification;
import com.kurachenko.entity.intarface.User;
import com.kurachenko.exception.PersistException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * this employee entity, employee its complex object and he's don't passive entity, employee make a task on project,
 * and he can has work on many projects
 * @author Pavel Kurachenko
 * @since 12/17/2016
 */

public class Employee implements User, Serializable {

    @JsonIgnore
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String description;
    private Department department;
    private Project[] projects;
    private Date hiredate;
    private Qualification levelQualification;

    public Employee() {
    }

    public Employee(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getName();
        this.lastName = employee.getSurname();
        this.login = employee.getLogin();
        this.password = employee.getPassword();
        this.description = employee.getDescription();
        this.levelQualification = employee.getLevelQualification();
        this.hiredate = employee.getHiredate();
        try {
            this.department = employee.getDepartment();
            this.projects = employee.getProjects();
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartment() throws PersistException {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Project[] getProjects() throws PersistException {
        return projects;
    }

    public void setProjects(Project[] projects) {
        this.projects = projects;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Qualification getLevelQualification() {
        return levelQualification;
    }

    public void setLevelQualification(Qualification levelQualification) {
        this.levelQualification = levelQualification;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", department=" + department +
                ", projects=" + Arrays.toString(projects) +
                ", hiredate=" + hiredate +
                ", levelQualification=" + levelQualification +
                '}';
    }
}
