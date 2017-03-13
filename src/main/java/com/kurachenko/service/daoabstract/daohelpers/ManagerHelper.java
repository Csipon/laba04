package com.kurachenko.service.daoabstract.daohelpers;

import com.kurachenko.entity.Project;
import com.kurachenko.entity.ProjectManager;
import com.kurachenko.exception.PersistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Pavel Kurachenko
 * @since 3/10/2017
 */
public abstract class ManagerHelper {

    public static List<ProjectManager> checkBusyManager(List<ProjectManager> managers, Integer idProject) throws PersistException {
        List<ProjectManager> result =  new ArrayList<>();

        for (ProjectManager pm : managers){
            if (!containsIdProject(pm.getManagedProjects(), idProject)){
                result.add(pm);
            }
        }
        return result;
    }

    private static boolean containsIdProject(Project[] projects, Integer idProject){
        for (Project project : projects){
            if (Objects.equals(project.getId(), idProject)){
                return true;
            }
        }
        return false;
    }
}
