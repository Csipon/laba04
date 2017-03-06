package com.kurachenko.service.daoabstract.daohelpers;

import com.kurachenko.entity.Employee;
import com.kurachenko.entity.Project;
import com.kurachenko.entity.ProjectManager;
import com.kurachenko.exception.PersistException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 2/5/2017
 */
public abstract class AdminHelper {

    public static void addManagedProject(ProjectManager manager, Project project) throws PersistException {
        List<Project> list = new ArrayList<>();
        if(manager.getManagedProjects() != null) {
            list.addAll(Arrays.asList(manager.getManagedProjects()));
        }
        list.add(project);
        manager.setManagedProjects(list.toArray(new Project[list.size()]));
    }

    public static void addSubordinate(ProjectManager manager, Employee employee) throws PersistException {
        List<Employee> list = new ArrayList<>();
        if (manager.getSubordinates() != null) {
            list.addAll(Arrays.asList(manager.getSubordinates()));
        }
        list.add(employee);
        manager.setSubordinates(list.toArray(new Employee[list.size()]));
    }

    public static List<Integer> listId(Employee[] employees){
        List<Integer> list = new ArrayList<>();
        for (Employee e : employees){
            list.add(e.getId());
        }
        return list;
    }
}
