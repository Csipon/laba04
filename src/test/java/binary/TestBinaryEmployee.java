package binary;

import com.kurachenko.entity.Employee;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.EmployeeBinaryService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 1/11/2017
 */
public class TestBinaryEmployee {
    private static EmployeeBinaryService service;


    @BeforeClass
    public static void setUpClass() throws PersistException {
        service = new EmployeeBinaryService();
    }

    @Test
    public void save() throws PersistException {
        for (int i = 1; i < 100; i++) {
        Employee employee = service.create();
            if (i % 2 == 0) {
                employee.setFirstName("Pasha");
                employee.setLastName("Kurachenko");
            }else {
                employee.setFirstName("Smeet");
                employee.setLastName("Smetanko");
            }
        employee.setDescription("HUMAN");
        service.persist(employee);
        }
        service.save();
    }


    @Test
    public void update() throws PersistException {
        List<Employee> list = service.getAll();
        for (int i = 1; i < list.size(); i++) {
            if (i % 7 == 0){
                Employee employee = list.get(i);
                employee.setDescription("IIIIIIIIIIIIIIIIIIII");
                service.update(employee);
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
