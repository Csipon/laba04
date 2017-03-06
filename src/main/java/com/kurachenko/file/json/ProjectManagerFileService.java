package com.kurachenko.file.json;

import com.kurachenko.entity.ProjectManager;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.abstr.AbstractFileService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 1/9/2017
 */
public class ProjectManagerFileService extends AbstractFileService<ProjectManager, Integer> {
    public ProjectManagerFileService() throws PersistException {
    }

    @Override
    protected ProjectManager createNewObject() throws IOException {
        return new ProjectManager();
    }

    @Override
    protected String getNameFile() {
        return "manager.json";
    }


}
