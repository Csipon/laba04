package com.kurachenko.entity;

import com.kurachenko.entity.intarface.Identified;

import java.util.Date;

/**
 * this is message entity, message is passive object, he's just store data of sender and his text in this message,
 * message store id sender as integer, id sprint, id task and id project as integer too, and yet date of sent
 * @author Pavel Kurachenko
 * @since 2/7/2017
 */
public class Message implements Identified<Integer> {
    private Integer id;
    private Date sent;
    private String description;
    private Integer idSender;
    private Integer messageSprint;
    private Integer messageTask;
    private Integer messageProject;
    private String text;

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

    public Integer getMessageSprint() {
        return messageSprint;
    }

    public void setMessageSprint(Integer messageSprint) {
        this.messageSprint = messageSprint;
    }

    public Integer getMessageTask() {
        return messageTask;
    }

    public void setMessageTask(Integer messageTask) {
        this.messageTask = messageTask;
    }

    public Integer getMessageProject() {
        return messageProject;
    }

    public void setMessageProject(Integer messageProject) {
        this.messageProject = messageProject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getIdSender() {
        return idSender;
    }

    public void setIdSender(Integer idSender) {
        this.idSender = idSender;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }


    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sent=" + sent +
                ", description='" + description + '\'' +
                ", idSender=" + idSender +
                ", messageSprint=" + messageSprint +
                ", messageTask=" + messageTask +
                ", messageProject=" + messageProject +
                ", text='" + text + '\'' +
                '}';
    }
}
