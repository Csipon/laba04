package com.kurachenko.file.json;

import com.kurachenko.entity.Sprint;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.abstr.AbstractFileService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 2/4/2017
 */
public class SprintFileService extends AbstractFileService<Sprint, Integer> {
    public SprintFileService() throws PersistException {
    }

    @Override
    protected Sprint createNewObject() throws IOException {
        return new Sprint();
    }

    @Override
    protected String getNameFile() {
        return "sprint.json";
    }
}
