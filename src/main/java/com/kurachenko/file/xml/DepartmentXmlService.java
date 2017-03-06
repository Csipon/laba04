package com.kurachenko.file.xml;

import com.kurachenko.entity.Department;
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
public class DepartmentXmlService extends AbstractXmlService<Department, Integer> {
    @Override
    protected Department createNewObject() throws IOException {
        return new Department();
    }

    @Override
    protected String getNameFile() {
        return "department.xml";
    }

    @Override
    protected EntityList<Department> getEntityList() {
        return new DepartmentList();
    }

    @Override
    protected Class<? extends EntityList> getListClass() {
        return DepartmentList.class;
    }

    @XmlRootElement(name = "departments")
    static class DepartmentList extends EntityList<Department> {

        @XmlElement(name = "department")
        List<Department> department;


        public DepartmentList() {
            department = new ArrayList<>();
        }

        @Override
        public List<Department> getList() {
            return department;
        }
    }
}
