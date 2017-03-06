package binary;

import com.kurachenko.entity.Employee;
import com.kurachenko.entity.ProjectManager;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.ProjectManagerBinaryService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 1/11/2017
 */
public class TestBinaryProjectManager {
    private static ProjectManagerBinaryService service;


    @BeforeClass
    public static void setUpClass() throws PersistException {
        service = new ProjectManagerBinaryService();
    }

    @Test
    public void save() throws PersistException {
        for (int i = 1; i < 100; i++) {
            ProjectManager manager = service.create();
            if (i % 2 == 0) {
                manager.setFirstName("ALAN");
                manager.setLastName("Kurachenko");
            }else {
                manager.setFirstName("Jim");
                manager.setLastName("Smetanko");
            }
            manager.setDescription("HUMAN");
            service.persist(manager);
        }
        service.save();
    }


    @Test
    public void update() throws PersistException {
        List<ProjectManager> list = service.getAll();
        for (int i = 1; i < list.size(); i++) {
            if (i % 7 == 0){
                ProjectManager manager = list.get(i);
                manager.setDescription("IIIIIIIIIIIIIIIIIIII");
                service.update(manager);
            }
        }
        service.save();
    }


    @Test
    public void getAll() throws PersistException {
        Integer count = 0;
        for (Employee employee : service.getAll()){
            count++;
            System.out.println(employee);
        }
        System.out.println(count);
    }
}
