package com.kurachenko.file.json.factory;

import com.kurachenko.entity.*;
import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.*;
import com.kurachenko.file.json.abstr.AbstractFileService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pavel Kurachenko
 * @since 2/20/2017
 */
public class JsonServiceFactory {
    private static JsonServiceFactory factory = new JsonServiceFactory();

    private Map<Class<? extends Identified>, AbstractFileService> cache;

    private JsonServiceFactory() {
        cache = new HashMap<>();

        try {
            cache.put(Employee.class, new EmployeeFileService());
            cache.put(Administrator.class, new AdminFileService());
            cache.put(Project.class, new ProjectFileService());
            cache.put(ProjectManager.class, new ProjectManagerFileService());
            cache.put(Customer.class, new CustomerFileService());
            cache.put(Task.class, new TaskFileService());
            cache.put(Sprint.class, new SprintFileService());
            cache.put(Department.class, new DepartmentFileService());
            cache.put(Journal.class, new JournalFileService());
        } catch (PersistException e) {
            e.printStackTrace();
        }

    }


    public AbstractFileService getService(Class clazz){
        return cache.get(clazz);
    }

    public static JsonServiceFactory getFactory() {
        return factory;
    }
}
