package com.kurachenko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurachenko.entity.intarface.Identified;

import java.io.Serializable;

/**
 * this department entity, passive entity which just view department of employee,
 * don't have complex fields
 * @author Pavel Kurachenko
 * @since 12/17/2016
 */
public class Department implements Identified<Integer>, Serializable {
    @JsonIgnore
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String description;
    private String name;
    private Integer number;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
