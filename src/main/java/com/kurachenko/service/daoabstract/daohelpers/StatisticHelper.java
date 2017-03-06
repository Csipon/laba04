package com.kurachenko.service.daoabstract.daohelpers;

import com.kurachenko.entity.Project;
import com.kurachenko.entity.Sprint;
import com.kurachenko.entity.Task;
import com.kurachenko.exception.PersistException;

import java.util.*;

/**
 * @author Pavel Kurachenko
 * @since 2/5/2017
 */
public abstract class StatisticHelper {
    public static Map<Integer, Double> getStatisticProject(Project project){
        Map<Integer, Double> result = new HashMap<>();
        try {
            Sprint[] sprints = project.getSprints();
            double completedProject = 0.0;
            double perOneSprint = 100.0 / sprints.length;
            for (Sprint sprint : sprints){
                Task[] tasks = getAllTask(sprint.getTasks());
                double  perOneTask = perOneSprint / tasks.length;
                double overSprint = 0.0;
                for (Task task : tasks){
                    if (task.isCompleted()){
                        overSprint += perOneTask;
                    }
                }
                result.put(sprint.getId(), overSprint);
                completedProject += overSprint;
            }
            result.put(project.getId(), completedProject);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Task[] getAllTask(Task[] tasks) throws PersistException {
        List<Task> list = new ArrayList<>();
        for (Task task : tasks){
            list.add(task);
            if (task.getTasks() != null){
                list.addAll(Arrays.asList(getAllTask(task.getTasks())));
            }
        }
        return list.toArray(new Task[list.size()]);
    }
}
