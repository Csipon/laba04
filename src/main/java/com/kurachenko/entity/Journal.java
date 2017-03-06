package com.kurachenko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * this journal entity, journal is passive object which just store task as object, id sprint from with task, start and
 * finish task as date and map where key id employee which make this task and the value is boolean variable which
 * view accept task employee or no
 * @author Pavel Kurachenko
 * @since 1/25/2017
 */
public class Journal implements Identified<Integer>, Serializable{
    @JsonIgnore
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String description;
    private Integer idSprint;
    private Task task;
    private Date startTask;
    private Date finishTask;
    private Map<Integer, Boolean> mapEmployee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task getTask() throws PersistException {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Map<Integer, Boolean> getMapEmployee() {
        return mapEmployee;
    }

    public void setMapEmployee(Map<Integer, Boolean> mapEmployee) {
        this.mapEmployee = mapEmployee;
    }

    public Date getStartTask() {
        return startTask;
    }

    public void setStartTask(Date startTask) {
        this.startTask = startTask;
    }

    public Date getFinishTask() {
        return finishTask;
    }

    public void setFinishTask(Date finishTask) {
        this.finishTask = finishTask;
    }

    public Integer getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", idSprint=" + idSprint +
                ", task=" + task +
                ", startTask=" + startTask +
                ", finishTask=" + finishTask +
                ", mapEmployee=" + mapEmployee +
                '}';
    }
}
