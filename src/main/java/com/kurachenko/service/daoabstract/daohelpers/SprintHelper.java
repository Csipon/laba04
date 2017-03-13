package com.kurachenko.service.daoabstract.daohelpers;

import com.kurachenko.entity.Project;
import com.kurachenko.entity.Sprint;
import com.kurachenko.entity.Task;
import com.kurachenko.exception.PersistException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 3/13/2017
 */
public abstract class SprintHelper {

    public static void setSprintInProject(Project project, Sprint sprint) throws PersistException {
        List<Sprint> list = new ArrayList<>();
        if (project.getSprints() != null) {
            list.addAll(Arrays.asList(project.getSprints()));
        }
        list.add(sprint);
        project.setSprints(list.toArray(new Sprint[list.size()]));
    }

    public static boolean finishSprint(Sprint sprint) throws PersistException {
        for (Task task : sprint.getTasks()){
            if (!TaskHelper.startTask(task) || !task.isCompleted()){
                return false;
            }
        }
        return true;
    }
}
