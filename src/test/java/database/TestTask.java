package database;

import com.kurachenko.entity.Task;
import com.kurachenko.entity.enums.Qualification;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.daohelpers.TaskHelper;
import com.kurachenko.service.daoimpl.TaskService;
import com.kurachenko.service.daoimpl.factory.OracleDaoFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 1/21/2017
 */
public class TestTask {

    private static OracleDaoFactory factory;
    private static TaskService service;


    @BeforeClass
    public static void setUpClass() throws PersistException, SQLException {
        factory = new OracleDaoFactory();
        service = new TaskService(factory, factory.dataSource().getConnection());
    }

    @Test
    public void create() throws PersistException {
        Task task = service.create();

        System.out.println(task);
    }

    @Test
    public void getByPk() throws PersistException {
        Task task = service.create();
        Task task1 = service.create();
        Task task2 = service.create();
        Task[] tasks = task.getTasks();

        List<Task> list = new ArrayList<>();
        list.addAll(Arrays.asList(tasks));
        list.add(task1);
        list.add(task2);

        task.setTasks(list.toArray(new Task[list.size()]));
        task.setName("Main task");
        task.setDescription("Java servlet API");
        task.setEstimate(10);
        task.setLevelQualification(Qualification.MIDDLE);
        service.update(task);
        task = service.getByPK(task.getId());
        System.out.println(task);
        System.out.println(Arrays.toString(task.getTasks()));
    }


    @Test
    public void test() throws PersistException {
//        List<Task> tasks = service.getAll();
//        for (Task task : tasks) {
//            System.out.println(task);
//        }
        Task task = service.getByPK(197);
        System.out.println(TaskHelper.startTask(task));
    }
}
