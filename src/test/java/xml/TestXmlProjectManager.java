package xml;

import com.kurachenko.entity.ProjectManager;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.xml.ProjectManagerXmlService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 2/21/2017
 */
public class TestXmlProjectManager {
    private static ProjectManagerXmlService service;


    @BeforeClass
    public static void setUpClass(){
        service = new ProjectManagerXmlService();
    }

    @Test
    public void create() throws JAXBException, PersistException {
        for (int i = 0; i < 10; i++) {
            ProjectManager obj = service.create();
            obj.setFirstName("pasha = " + i);
            service.persist(obj);
        }
        service.save();
        for (ProjectManager e : service.getAll()){
            System.out.println(e);
        }
    }


    @Test
    public void getAll() throws PersistException {
        for (ProjectManager e : service.getAll()){
            System.out.println(e);
        }
    }


    @Test
    public void update() throws PersistException {
        for (ProjectManager e : service.getAll()){
            e.setLogin("123123132");
            System.out.println(e);
            service.update(e);
        }

        service.save();
    }



    @Test
    public void delete() throws JAXBException, PersistException {
        List<ProjectManager> list = service.getAll();


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
