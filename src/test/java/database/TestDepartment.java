package database;

import com.kurachenko.entity.Department;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.DepartmentService;
import com.kurachenko.service.daoimpl.factory.OracleDaoFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 12/29/2016
 */
public class TestDepartment {

    private static OracleDaoFactory factory;
    private static DepartmentService service;

    @BeforeClass
    public static void setUpClass() throws PersistException {
        factory = new OracleDaoFactory();
        service = new DepartmentService(factory, factory.getContext());
    }

    @Test
    public void create() throws PersistException {
        Department department = service.create();

        System.out.println(department);
    }


    @Test
    public void readUpdate() throws PersistException {
        Department department = service.create();

        department.setName("C#");
        department.setDescription("Department by C#");
        department.setNumber(222);

        service.update(department);
    }


    @Test
    public void delete() throws PersistException {
        Department department = service.create();

        System.out.println(department);

        service.delete(department);
    }


    @Test
    public void getAll() throws PersistException {
        long before = System.nanoTime();
        List<Department> departments = service.getAll();
        long after = System.nanoTime();

        for (Department department : departments){
            System.out.println(department);
        }

        System.out.println((after - before) / 1000.0);
    }
}
