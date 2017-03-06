package com.kurachenko.file.xml.factory;

import com.kurachenko.entity.*;
import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.file.xml.*;
import com.kurachenko.file.xml.abstr.AbstractXmlService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pavel Kurachenko
 * @since 2/20/2017
 */
public class XmlServiceFactory {

    private static XmlServiceFactory factory = new XmlServiceFactory();

    private Map<Class<? extends Identified>, AbstractXmlService> cache;

    private XmlServiceFactory() {
        cache = new HashMap<>();

        cache.put(Employee.class, new EmployeeXmlService());
        cache.put(Administrator.class, new AdminXmlService());
        cache.put(Project.class, new ProjectXmlService());
        cache.put(ProjectManager.class, new ProjectManagerXmlService());
        cache.put(Customer.class, new CustomerXmlService());
        cache.put(Task.class, new TaskXmlService());
        cache.put(Sprint.class, new SprintXmlService());
        cache.put(Message.class, new MessageXmlService());
        cache.put(Department.class, new DepartmentXmlService());
        cache.put(Journal.class, new JournalXmlService());
    }


    public AbstractXmlService getService(Class clazz){
        return cache.get(clazz);
    }

    public static XmlServiceFactory getFactory() {
        return factory;
    }
}
