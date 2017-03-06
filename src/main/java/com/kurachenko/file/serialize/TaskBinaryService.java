package com.kurachenko.file.serialize;

import com.kurachenko.entity.Task;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.abstr.AbstractSerializeService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 2/4/2017
 */
public class TaskBinaryService extends AbstractSerializeService<Task, Integer> {
    public TaskBinaryService() throws PersistException {
    }

    @Override
    protected Task createNewObject() throws IOException {
        return new Task();
    }

    @Override
    protected String getNameFile() {
        return "task.txt";
    }
}
