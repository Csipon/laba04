package com.kurachenko.file.serialize;

import com.kurachenko.entity.Sprint;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.abstr.AbstractSerializeService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 2/4/2017
 */
public class SprintBinaryService extends AbstractSerializeService<Sprint,Integer> {
    public SprintBinaryService() throws PersistException {
    }

    @Override
    protected Sprint createNewObject() throws IOException {
        return new Sprint();
    }

    @Override
    protected String getNameFile() {
        return "sprint.txt";
    }
}
