package binary;

import com.kurachenko.entity.Project;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.ProjectBinaryService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 1/11/2017
 */
public class TestBinaryProject {
    private static ProjectBinaryService service;


    @BeforeClass
    public static void setUpClass() throws PersistException {
        service = new ProjectBinaryService();
    }

    @Test
    public void save() throws PersistException {
        for (int i = 1; i < 100; i++) {
            Project project = service.create();
            if (i % 2 == 0) {
                project.setName("IOS");
            } else {
                project.setName("ANDROID");
            }
            project.setDescription("The best mobile platform");
            service.persist(project);
        }
        service.save();
    }


    @Test
    public void update() throws PersistException {
        List<Project> list = service.getAll();
        for (int i = 1; i < list.size(); i++) {
            if (i % 7 == 0) {
                Project project = list.get(i);
                project.setPlanedBudget(199239.32);
                service.update(project);
            }
        }
        service.save();
    }


    @Test
    public void getAll() throws PersistException {
        Integer count = 0;
        for (Project project : service.getAll()) {
            count++;
            System.out.println(project);
        }
        System.out.println(count);
    }
}
