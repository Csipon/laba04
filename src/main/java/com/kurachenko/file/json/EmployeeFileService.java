package com.kurachenko.file.json;

import com.kurachenko.entity.Employee;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.abstr.AbstractFileService;

import java.io.*;

/**
 * @author Pavel Kurachenko
 * @since 1/9/2017
 */
public class EmployeeFileService extends AbstractFileService<Employee, Integer> {

    public EmployeeFileService() throws PersistException {
    }

    public Employee createNewObject() throws IOException {
        return new Employee();
    }

    @Override
    protected String getNameFile() {
        return "employee.json";
    }


}
