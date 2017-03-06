package com.kurachenko.file.serialize;

import com.kurachenko.entity.Employee;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.abstr.AbstractSerializeService;

import java.io.IOException;

/**
 * @author Pavel Kurachenko
 * @since 1/9/2017
 */
public class EmployeeBinaryService extends AbstractSerializeService<Employee, Integer> {

    public EmployeeBinaryService() throws PersistException {
    }

    public Employee createNewObject() throws IOException {
        return new Employee();
    }

    @Override
    protected String getNameFile() {
        return "employee.txt";
    }


}
