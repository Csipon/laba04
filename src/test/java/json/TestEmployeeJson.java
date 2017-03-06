package json;

import com.kurachenko.entity.Department;
import com.kurachenko.entity.Employee;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.EmployeeFileService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 1/5/2017
 */
public class TestEmployeeJson {
    private static EmployeeFileService service;
    @BeforeClass
    public static void setUpClass() throws IOException, PersistException {
        service = new EmployeeFileService();
    }

    @Test
    public void create() throws PersistException {
        Employee employee = service.create();
        System.out.println(employee);
    }


    @Test
    public void save() throws PersistException {
        Employee employee = service.create();
        employee.setFirstName("Smeet");
        employee.setLastName("Smetanko");
        service.persist(employee);
    }

    @Test
    public void read() throws PersistException {
        List<Employee> list = service.getAll();
        for(Employee e : list){
            System.out.println(e);
        }
    }



    @Test
    public void update() throws PersistException {
        Employee employee = service.getByPK(1);
        System.out.println(employee);
        employee.setHiredate(new Date());
        employee.setLogin("csipon");
        employee.setPassword("123qwe");
        Department department = new Department();
        department.setId(1);
        employee.setDepartment(department);
        service.update(employee);
    }

    @Test
    public void delete() throws PersistException {
        Employee employee = service.getByPK(2);
        System.out.println(employee);
        service.delete(employee);
    }
}
