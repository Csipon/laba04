package com.kurachenko.service.daoabstract.daohelpers;

import com.kurachenko.entity.Employee;
import com.kurachenko.entity.Project;
import com.kurachenko.exception.PersistException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 1/27/2017
 */
public abstract class EmployeeHelper {


    /**
     * Method for remove employee which already have project
     * @param employees list all employees
     */
    public static List<Employee> getFreeEmps(List<Employee> employees, Integer projectId) throws PersistException {
        for (int i = employees.size() - 1; i >= 0 ; i--){
            for (Project project : employees.get(i).getProjects()){
                if (equalsTwoInts(project.getId(), projectId)){
                    employees.remove(employees.get(i));
                    break;
                }
            }
        }
        return employees;
    }

    public static Employee setProject(Employee employee, Project project) throws PersistException {
        List<Project> projects = new ArrayList<>();
        if (employee.getProjects() != null){
            projects.addAll(Arrays.asList(employee.getProjects()));
        }
        projects.add(project);
        employee.setProjects(projects.toArray(new Project[projects.size()]));
        return employee;
    }

    private static boolean equalsTwoInts(int a, int b){
        return a == b;
    }

    public static List<Employee> removeEmployee(List<Employee> list, Employee employee){
        for (int i = list.size() - 1 ; i >= 0; i--) {
            int emp = employee.getId();
            int listEmp = list.get(i).getId();
            if (emp == listEmp){
                list.remove(i);
                break;
            }
        }
        return list;
    }
}
