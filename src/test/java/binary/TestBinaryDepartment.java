package binary;

import com.kurachenko.entity.Department;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.DepartmentBinaryService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 1/11/2017
 */
public class TestBinaryDepartment {
    private static DepartmentBinaryService service;


    @BeforeClass
    public static void setUpClass() throws PersistException {
        service = new DepartmentBinaryService();
    }

    @Test
    public void save() throws PersistException {
        for (int i = 1; i < 100; i++) {
            Department department = service.create();
            if (i % 2 == 0) {
                department.setName("DEVELOPERS");
            } else {
                department.setName("ANALIST");
            }
            department.setDescription("THIS is MAIN DEPARTMENT");
            service.persist(department);
        }
        service.save();
    }


    @Test
    public void update() throws PersistException {
        List<Department> list = service.getAll();
        for (int i = 1; i < list.size(); i++) {
            if (i % 7 == 0) {
                Department department = list.get(i);
                department.setDescription("OOOOOOOOOOOOOOOOO");
                service.update(department);
            }
        }
        service.save();
    }


    @Test
    public void getAll() throws PersistException {
        Integer count = 0;
        for (Department department : service.getAll()) {
            count++;
            System.out.println(department);
        }
        System.out.println(count);
    }
}
