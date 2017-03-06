package com.kurachenko.file.json;

import com.kurachenko.entity.Project;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.abstr.AbstractFileService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 1/9/2017
 */
public class ProjectFileService extends AbstractFileService<Project, Integer> {
    public ProjectFileService() throws PersistException {
    }

    @Override
    protected Project createNewObject() throws IOException {
        return new Project();
    }

    @Override
    protected String getNameFile() {
        return "project.json";
    }


}
