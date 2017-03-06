package xml;

import com.kurachenko.entity.Employee;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.xml.EmployeeXmlService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 2/20/2017
 */
public class TestXmlEmployee {
    private static EmployeeXmlService service;


    @BeforeClass
    public static void setUpClass(){
        service = new EmployeeXmlService();
    }

    @Test
    public void create() throws JAXBException, PersistException {
        for (int i = 0; i < 10; i++) {
            Employee employee = service.create();
            employee.setLastName("pasha = " + i);
            service.persist(employee);
        }
        service.save();
        for (Employee e : service.getAll()){
            System.out.println(e);
        }
    }


    @Test
    public void getAll() throws PersistException {
        for (Employee e : service.getAll()){
            System.out.println(e);
        }
    }


    @Test
    public void update() throws PersistException {
        for (Employee e : service.getAll()){
            e.setLogin("123123123");
            System.out.println(e);
            service.update(e);
        }

        service.save();
    }



    @Test
    public void delete() throws JAXBException, PersistException {
        List<Employee> list = service.getAll();


        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0){
                service.delete(list.get(i));
                System.out.println("delete = " + i);
            }
        }
        service.save();
        System.out.println("All good");
    }
}




