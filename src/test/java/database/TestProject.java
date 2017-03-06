package database;

import com.kurachenko.entity.Project;
import com.kurachenko.entity.Sprint;
import com.kurachenko.entity.Task;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.ProjectService;
import com.kurachenko.service.daoimpl.factory.OracleDaoFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * @author Pavel Kurachenko
 * @since 12/28/2016
 */
public class TestProject {
    private static OracleDaoFactory factory;
    private static ProjectService service;

    @BeforeClass
    public static void setUpClass() throws PersistException, SQLException {
        factory = new OracleDaoFactory();
        service = new ProjectService(factory, factory.getContext());
    }


    @Test
    public void saveProject() throws PersistException {
        Project project = service.create();
        System.out.println(project);
        service.delete(project);
    }


    @Test
    public void updateProject() throws PersistException {
        Project project = service.create();
        project.setName("GOOGLE");
        service.update(project);
        Project project1 = service.getByPK(project.getId());
        assertTrue(project1.getName().equals("GOOGLE"));
        service.delete(project1);
    }


    @Test
    public void getByPK() throws PersistException {
        Project project = service.getByPK(148);
        Map<Integer, Double> map = getStatisticProject(project);
        for (Map.Entry<Integer, Double> m : map.entrySet()){
            System.out.println(m.getKey() + " = " + m.getValue() + "%");
        }
    }

    @Test
    public void getAll() throws PersistException {
        List<Project> projects = service.getAll();
        for (Project project : projects){
            System.out.println(project);
        }
    }


    private Map<Integer, Double> getStatisticProject(Project project){
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

    private Task[] getAllTask(Task[] tasks) throws PersistException {
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
