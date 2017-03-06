package com.kurachenko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * this project entity, project is passive object but employees making a project and therefore project is very complex
 * entity, project store in self date of start and finish, array sprint of project, project customer and such variables
 * as planed budget, paid, additional payments
 * @author Pavel Kurachenko
 * @since 12/17/2016
 */
public class Project implements Identified<Integer>, Serializable {
    @JsonIgnore
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String description;
    private String name;
    private Date start;
    private Date finish;
    private Sprint[] sprints;
    private Customer customer;
    private Double planedBudget;
    private Double paid;
    private Double additionalPayments;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() throws PersistException {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getPlanedBudget() {
        return planedBudget;
    }

    public void setPlanedBudget(Double planedBudget) {
        this.planedBudget = planedBudget;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public Double getAdditionalPayments() {
        return additionalPayments;
    }

    public void setAdditionalPayments(Double additionalPayments) {
        this.additionalPayments = additionalPayments;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public Sprint[] getSprints() throws PersistException {
        return sprints;
    }

    public void setSprints(Sprint[] sprints) {
        this.sprints = sprints;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", finish=" + finish +
                ", sprints=" + Arrays.toString(sprints) +
                ", customer=" + customer +
                ", planedBudget=" + planedBudget +
                ", paid=" + paid +
                ", additionalPayments=" + additionalPayments +
                '}';
    }
}
