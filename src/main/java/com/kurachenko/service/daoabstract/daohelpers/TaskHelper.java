package com.kurachenko.service.daoabstract.daohelpers;

import com.kurachenko.entity.Sprint;
import com.kurachenko.entity.Task;
import com.kurachenko.exception.PersistException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 2/5/2017
 */
public abstract class TaskHelper {


    public static void setTaskInSprint(Sprint sprint, Task task) throws PersistException {
        List<Task> list = new ArrayList<>();
        if (sprint.getTasks() != null) {
            list.addAll(Arrays.asList(sprint.getTasks()));
        }
        list.add(task);
        sprint.setTasks(list.toArray(new Task[list.size()]));
    }

    public static boolean startTask(Task task) throws PersistException {
        for (Task t : task.getTasks()) {
            if (t.getTasks() != null) {
                startTask(t);
            } else {
                if (!t.isCompleted()) {
                    return false;
                }
            }
        }
        return true;
    }
}
