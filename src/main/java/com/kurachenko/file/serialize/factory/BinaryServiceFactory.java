package com.kurachenko.file.serialize.factory;

import com.kurachenko.entity.*;
import com.kurachenko.entity.intarface.Identified;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.*;
import com.kurachenko.file.serialize.abstr.AbstractSerializeService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pavel Kurachenko
 * @since 2/20/2017
 */
public class BinaryServiceFactory {
    private static BinaryServiceFactory factory = new BinaryServiceFactory();

    private Map<Class<? extends Identified>, AbstractSerializeService> cache;

    private BinaryServiceFactory() {
        cache = new HashMap<>();

        try {
            cache.put(Employee.class, new EmployeeBinaryService());
            cache.put(Administrator.class, new AdminBinaryService());
            cache.put(Project.class, new ProjectBinaryService());
            cache.put(ProjectManager.class, new ProjectManagerBinaryService());
            cache.put(Customer.class, new CustomerBinaryService());
            cache.put(Task.class, new TaskBinaryService());
            cache.put(Sprint.class, new SprintBinaryService());
            cache.put(Department.class, new DepartmentBinaryService());
            cache.put(Journal.class, new JournalBinaryService());
        } catch (PersistException e) {
            e.printStackTrace();
        }

    }


    public AbstractSerializeService getService(Class clazz){
        return cache.get(clazz);
    }

    public static BinaryServiceFactory getFactory() {
        return factory;
    }
}
