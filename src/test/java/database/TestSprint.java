package database;

import com.kurachenko.entity.Sprint;
import com.kurachenko.entity.Task;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.SprintService;
import com.kurachenko.service.daoimpl.TaskService;
import com.kurachenko.service.daoimpl.factory.OracleDaoFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Pavel Kurachenko
 * @since 1/22/2017
 */
public class TestSprint {


    private static OracleDaoFactory factory;
    private static SprintService service;
    private static TaskService taskService;


    @BeforeClass
    public static void setUpClass() throws PersistException {
        factory = new OracleDaoFactory();
        service = new SprintService(factory, factory.getContext());
        taskService = new TaskService(factory, factory.getContext());
    }



    @Test
    public void create() throws PersistException {
        Sprint sprint = service.create();

        System.out.println(sprint);
    }



    @Test
    public void update() throws PersistException {
        Sprint sprint = service.getByPK(134);
        Task task = taskService.getByPK(131);
        sprint.setTasks(new Task[]{task});
        service.update(sprint);
        System.out.println("sprint first update");


        Sprint sprint1 = service.create();
        sprint1.setPreviousSprint(service.getByPK(134));

        service.update(sprint1);
        System.out.println("sprint second update");
    }

    @Test
    public void check() throws PersistException {
        Sprint sprint = service.getByPK(157);

        System.out.println(sprint);
        System.out.println(Arrays.toString(sprint.getTasks()));
    }
}
