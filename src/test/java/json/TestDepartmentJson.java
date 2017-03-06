package json;

import com.kurachenko.entity.Department;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.DepartmentFileService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 1/10/2017
 */
public class TestDepartmentJson {
    private static DepartmentFileService service;

    @BeforeClass
    public static void setUpClass() throws IOException, PersistException {
        service = new DepartmentFileService();
    }

    @Test
    public void create() throws PersistException {
        Department department = service.create();
        System.out.println(department);
    }


    @Test
    public void save() throws PersistException {
        for (int i = 0; i < 100; i++) {
            Department department = service.create();
            if (i % 2 == 0) {
                department.setName("ANALIST");
                department.setNumber(152);
            } else {
                department.setName("DEVELOPERS");
                department.setNumber(45);
            }
            service.persist(department);
        }
    }

    @Test
    public void read() throws PersistException {
        List<Department> list = service.getAll();
        for (Department d : list) {
            System.out.println(d);
        }
    }


    @Test
    public void update() throws PersistException {
        for (int i = 1; i < 100; i++) {
            if (i % 3 == 0) {
                Department department = service.getByPK(i);
                System.out.println(department);
                department.setDescription("Is department with very high specialists");
                service.update(department);
            }
        }
    }

    @Test
    public void delete() throws PersistException {
        for (int i = 1; i < 100; i++) {
            if (i % 5 == 0) {
                Department department = service.getByPK(i);
                System.out.println(department);
                service.delete(department);
            }
        }
    }
}
