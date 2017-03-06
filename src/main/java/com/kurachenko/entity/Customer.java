package com.kurachenko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurachenko.entity.intarface.User;

import java.io.Serializable;

/**
 * this customer entity which haven't complex field, customer just make orders of project and watch for his completing
 * @author Pavel Kurachenko
 * @since 12/17/2016
 */
public class Customer implements User, Serializable{

    @JsonIgnore
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String companyName;
    private String description;

    public Customer() {
    }

    public Customer(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.surname = customer.getSurname();
        this.login = customer.getLogin();
        this.password = customer.getPassword();
        this.description = customer.getDescription();
        this.companyName = customer.getCompanyName();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer i) {
        this.id = i;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", companyName='" + companyName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
