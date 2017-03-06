package json;

import com.kurachenko.entity.Department;
import com.kurachenko.entity.ProjectManager;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.DepartmentFileService;
import com.kurachenko.file.json.ProjectManagerFileService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 1/10/2017
 */
public class TestProjectManagerJson {
    private static ProjectManagerFileService service;
    private static DepartmentFileService departmentFileService;

    @BeforeClass
    public static void setUpClass() throws PersistException {
        service = new ProjectManagerFileService();
        departmentFileService = new DepartmentFileService();
    }

    @Test
    public void create() throws PersistException {
        ProjectManager projectManager = service.create();
        System.out.println(projectManager);
    }


    @Test
    public void save() throws PersistException {
        for (int i = 1; i < 100; i++) {
            ProjectManager projectManager = service.create();
            projectManager.setFirstName("Antony");
            projectManager.setLastName("Hokins");
            service.persist(projectManager);
        }
    }

    @Test
    public void read() throws PersistException {
        List<ProjectManager> list = service.getAll();
        for (ProjectManager p : list) {
            System.out.println(p);
        }
    }


    @Test
    public void update() throws PersistException {
        Department department1 = departmentFileService.getByPK(1);
        Department department6 = departmentFileService.getByPK(6);
        for (int i = 1; i < 100; i++) {
            ProjectManager projectManager = service.getByPK(i);
            if (i % 2 == 0) {
                projectManager.setHiredate(new Date());
                projectManager.setLogin("antony");
                projectManager.setPassword("11111111");
                projectManager.setDepartment(department1);
            } else {
                projectManager.setLogin("QPQOWO");
                projectManager.setHiredate(new Date());
                projectManager.setPassword("000000000");
                projectManager.setDepartment(department6);
            }
            service.update(projectManager);
        }
    }

    @Test
    public void delete() throws PersistException {
        for (int i = 1; i < 100; i++) {
            if (i % 4 == 0) {
                ProjectManager projectManager = service.getByPK(i);
                System.out.println(projectManager);
                service.delete(projectManager);
            }
        }
    }
}
