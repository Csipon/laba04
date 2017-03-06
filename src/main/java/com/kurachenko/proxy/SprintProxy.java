package com.kurachenko.proxy;

import com.kurachenko.entity.Sprint;
import com.kurachenko.entity.Task;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.SprintService;

/**
 * @author Pavel Kurachenko
 * @since 1/22/2017
 */
public class SprintProxy extends Sprint {

    private SprintService service;

    public SprintProxy(SprintService service) {
        this.service = service;
    }


    @Override
    public Sprint getPreviousSprint() throws PersistException {
        if (super.getPreviousSprint() == null){
            setPreviousSprint((Sprint) service.getObject(this, Sprint.class));
        }
        return super.getPreviousSprint();
    }

    @Override
    public Task[] getTasks() throws PersistException {
        if (super.getTasks() == null){
            setTasks((Task[]) service.getArrays(this, "tasks"));
        }
        return super.getTasks();
    }
}
