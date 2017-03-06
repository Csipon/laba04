package com.kurachenko.proxy;

import com.kurachenko.entity.Journal;
import com.kurachenko.entity.Task;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.JournalService;

import java.util.Map;

/**
 * @author Pavel Kurachenko
 * @since 1/25/2017
 */
public class JournalProxy extends Journal{
    private JournalService service;

    public JournalProxy(JournalService service) {
        this.service = service;
    }

    @Override
    public Task getTask() throws PersistException {
        if (super.getTask() == null){
            setTask((Task) service.getObject(this, Task.class));
        }
        return super.getTask();
    }

    @Override
    public Map<Integer, Boolean> getMapEmployee() {
        if (super.getMapEmployee() == null){
            setMapEmployee((Map<Integer, Boolean>) service.getMap(this, "mapEmployee"));
        }
        return super.getMapEmployee();
    }
}
