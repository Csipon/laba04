package xml;

import com.kurachenko.entity.Department;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.xml.DepartmentXmlService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 2/21/2017
 */
public class TestXmlDepartment {
    private static DepartmentXmlService service;


    @BeforeClass
    public static void setUpClass(){
        service = new DepartmentXmlService();
    }

    @Test
    public void create() throws JAXBException, PersistException {
        for (int i = 0; i < 10; i++) {
            Department obj = service.create();
            obj.setName("dept = " + i);
            service.persist(obj);
        }
        service.save();
        for (Department e : service.getAll()){
            System.out.println(e);
        }
    }


    @Test
    public void getAll() throws PersistException {
        for (Department e : service.getAll()){
            System.out.println(e);
        }
    }


    @Test
    public void update() throws PersistException {
        for (Department e : service.getAll()){
            e.setNumber(1221);
            System.out.println(e);
            service.update(e);
        }

        service.save();
    }



    @Test
    public void delete() throws JAXBException, PersistException {
        List<Department> list = service.getAll();


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
