package xml;

import com.kurachenko.entity.Project;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.xml.ProjectXmlService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 2/21/2017
 */
public class TestXmlProject {
    private static ProjectXmlService service;


    @BeforeClass
    public static void setUpClass(){
        service = new ProjectXmlService();
    }

    @Test
    public void create() throws JAXBException, PersistException {
        for (int i = 0; i < 10; i++) {
            Project obj = service.create();
            obj.setName("project = " + i);
            service.persist(obj);
        }
        service.save();
        for (Project e : service.getAll()){
            System.out.println(e);
        }
    }


    @Test
    public void getAll() throws PersistException {
        for (Project e : service.getAll()){
            System.out.println(e);
        }
    }


    @Test
    public void update() throws PersistException {
        for (Project e : service.getAll()){
            e.setPaid(123123123123.123);
            System.out.println(e);
            service.update(e);
        }

        service.save();
    }



    @Test
    public void delete() throws JAXBException, PersistException {
        List<Project> list = service.getAll();


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
