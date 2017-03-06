package com.kurachenko.file.serialize;

import com.kurachenko.entity.ProjectManager;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.abstr.AbstractSerializeService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 1/9/2017
 */
public class ProjectManagerBinaryService extends AbstractSerializeService<ProjectManager, Integer> {
    public ProjectManagerBinaryService() throws PersistException {
    }

    @Override
    protected ProjectManager createNewObject() throws IOException {
        return new ProjectManager();
    }

    @Override
    protected String getNameFile() {
        return "manager.txt";
    }


}
