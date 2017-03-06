package com.kurachenko.file.serialize;

import com.kurachenko.entity.Department;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.abstr.AbstractSerializeService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 1/9/2017
 */
public class DepartmentBinaryService extends AbstractSerializeService<Department, Integer> {
    public DepartmentBinaryService() throws PersistException {
    }

    @Override
    protected Department createNewObject() throws IOException {
        return new Department();
    }

    @Override
    protected String getNameFile() {
        return "department.txt";
    }
}
