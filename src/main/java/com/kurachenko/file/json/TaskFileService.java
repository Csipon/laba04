package com.kurachenko.file.json;

import com.kurachenko.entity.Task;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.abstr.AbstractFileService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 2/4/2017
 */
public class TaskFileService extends AbstractFileService<Task, Integer>{
    public TaskFileService() throws PersistException {
    }

    @Override
    protected Task createNewObject() throws IOException {
        return new Task();
    }

    @Override
    protected String getNameFile() {
        return "task.json";
    }
}
