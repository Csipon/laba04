package com.kurachenko.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kurachenko.entity.intarface.User;
import org.springframework.security.access.prepost.PostAuthorize;

import java.io.Serializable;

/**
 * this entity which will be answer for all work regard admin
 * @author Pavel Kurachenko
 * @since 1/19/2017
 */
public class Administrator implements User, Serializable{
    @JsonIgnore
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String description;

    public Administrator() {
    }

    public Administrator(Administrator administrator) {
        this.id = administrator.getId();
        this.name = administrator.getName();
        this.surname = administrator.getSurname();
        this.login = administrator.getLogin();
        this.password = administrator.getPassword();
        this.description = administrator.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
