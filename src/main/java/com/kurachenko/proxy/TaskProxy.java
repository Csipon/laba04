package com.kurachenko.proxy;

import com.kurachenko.entity.Task;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.TaskService;

/**
 * @author Pavel Kurachenko
 * @since 1/21/2017
 */
public class TaskProxy extends Task {
    private TaskService service;

    public TaskProxy(TaskService service) {
        this.service = service;
    }

    @Override
    public Task[] getTasks() throws PersistException {
        if (super.getTasks() == null){
            super.setTasks((Task[]) service.getArrays(this, "tasks"));
        }
        return super.getTasks();
    }
}
