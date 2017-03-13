package com.kurachenko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * this sprint entity, sprint is passive object but sprint have array of tasks which employees making, this entity is
 * complex because it has array of tasks, previously sprint
 * @author Pavel Kurachenko
 * @since 1/21/2017
 */
public class Sprint implements Identified<Integer>, Serializable {
    @JsonIgnore
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String description;
    private Date created;
    private String name;
    private Sprint previousSprint;
    private Task[] tasks;
    private Boolean started = false;
    private Boolean finished = false;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Sprint getPreviousSprint() throws PersistException {
        return previousSprint;
    }

    public void setPreviousSprint(Sprint previousSprint) {
        this.previousSprint = previousSprint;
    }

    public Task[] getTasks() throws PersistException {
        return tasks;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Boolean isStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", name='" + name + '\'' +
                ", previousSprint=" + previousSprint +
                ", tasks=" + Arrays.toString(tasks) +
                ", started=" + started +
                ", finished=" + finished +
                '}';
    }
}
