package json;

import com.kurachenko.entity.Project;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.ProjectFileService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 1/10/2017
 */
public class TestProjectJson {
    private static ProjectFileService service;

    @BeforeClass
    public static void setUpClass() throws IOException, PersistException {
        service = new ProjectFileService();
    }

    @Test
    public void create() throws PersistException {
        Project project = service.create();
        System.out.println(project);
    }


    @Test
    public void save() throws PersistException {
        for (int i = 1; i < 100; i++) {
            Project project = service.create();
            if (i % 2 == 0) {
                project.setName("ANDROID");
                project.setPlanedBudget(23414.234);
            } else {
                project.setName("MICROSOFT");
                project.setPlanedBudget(23414.234);
            }
            service.persist(project);
        }
    }

    @Test
    public void read() throws PersistException {
        List<Project> list = service.getAll();
        for (Project project : list) {
            System.out.println(project);
        }
    }


    @Test
    public void update() throws PersistException {
        for (int i = 1; i < 100; i++) {
            if (i % 3 == 0) {
                Project project = service.getByPK(i);
                System.out.println(project);
                project.setDescription("This is project very cool!");
                service.update(project);
            }
        }
    }

    @Test
    public void delete() throws PersistException {
        for (int i = 1; i < 100; i++) {
            if (i % 5 == 0) {
                Project project = service.getByPK(i);
                System.out.println(project);
                service.delete(project);
            }
        }
    }
}
