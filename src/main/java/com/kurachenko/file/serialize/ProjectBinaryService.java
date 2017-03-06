package com.kurachenko.file.serialize;

import com.kurachenko.entity.Project;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.abstr.AbstractSerializeService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 1/9/2017
 */
public class ProjectBinaryService extends AbstractSerializeService<Project, Integer> {
    public ProjectBinaryService() throws PersistException {
    }

    @Override
    protected Project createNewObject() throws IOException {
        return new Project();
    }

    @Override
    protected String getNameFile() {
        return "project.txt";
    }


}
