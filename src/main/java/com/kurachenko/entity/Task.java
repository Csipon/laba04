package com.kurachenko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurachenko.entity.enums.Qualification;
import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;

import java.io.Serializable;
import java.util.Arrays;

/**
 * this task entity, task is passive object and complex, because task can have a dependent tasks
 * @author Pavel Kurachenko
 * @since 1/21/2017
 */
public class Task implements Identified<Integer>, Serializable {
    @JsonIgnore
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String description;
    private String name;
    private Task[] tasks;
    private Integer estimate;
    private Boolean isStarted = false;
    private Boolean isCompleted = false;
    private Qualification levelQualification;


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

    public Task[] getTasks() throws PersistException {
        return tasks;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }

    public Boolean isStarted() {
        return isStarted;
    }

    public void setStarted(Boolean started) {
        isStarted = started;
    }

    public Boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Qualification getLevelQualification() {
        return levelQualification;
    }

    public void setLevelQualification(Qualification levelQualification) {
        this.levelQualification = levelQualification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEstimate() {
        return estimate;
    }

    public void setEstimate(Integer estimate) {
        this.estimate = estimate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", tasks=" + Arrays.toString(tasks) +
                ", estimate=" + estimate +
                ", isStarted=" + isStarted +
                ", isCompleted=" + isCompleted +
                ", levelQualification=" + levelQualification +
                '}';
    }
}
