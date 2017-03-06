package com.kurachenko.file.xml;

import com.kurachenko.entity.Employee;
import com.kurachenko.file.xml.abstr.AbstractXmlService;
import com.kurachenko.file.xml.abstr.EntityList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 2/20/2017
 */
public class EmployeeXmlService extends AbstractXmlService<Employee, Integer> {
    public EmployeeXmlService() {
    }

    @Override
    protected Employee createNewObject() throws IOException {
        return new Employee();
    }

    @Override
    protected String getNameFile() {
        return "employee.xml";
    }

    @Override
    protected EntityList<Employee> getEntityList() {
        return new EmployeeList();
    }

    @Override
    protected Class getListClass() {
        return EmployeeList.class;
    }


    @XmlRootElement(name = "employees")
    static class EmployeeList extends EntityList<Employee> {

        @XmlElement(name = "employee")
        List<Employee> employee;


        public EmployeeList() {
            employee = new ArrayList<>();
        }

        @Override
        public List<Employee> getList() {
            return employee;
        }
    }
}
