package com.kurachenko.file.json;

import com.kurachenko.entity.Department;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.abstr.AbstractFileService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 1/9/2017
 */
public class DepartmentFileService extends AbstractFileService<Department, Integer> {
    public DepartmentFileService() throws PersistException {
    }

    @Override
    protected Department createNewObject() throws IOException {
        return new Department();
    }

    @Override
    protected String getNameFile() {
        return "department.json";
    }
}
