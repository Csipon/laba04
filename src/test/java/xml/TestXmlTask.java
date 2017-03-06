package xml;

import com.kurachenko.entity.Task;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.xml.TaskXmlService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 2/21/2017
 */
public class TestXmlTask {

    private static TaskXmlService service;


    @BeforeClass
    public static void setUpClass(){
        service = new TaskXmlService();
    }

    @Test
    public void create() throws JAXBException, PersistException {
        for (int i = 0; i < 10; i++) {
            Task o = service.create();
            o.setName("task = " + i);
            service.persist(o);
        }
        service.save();
        for (Task e : service.getAll()){
            System.out.println(e);
        }
    }


    @Test
    public void getAll() throws PersistException {
        for (Task e : service.getAll()){
            System.out.println(e);
        }
    }


    @Test
    public void update() throws PersistException {
        for (Task e : service.getAll()){
            e.setEstimate(55555);
            System.out.println(e);
            service.update(e);
        }

        service.save();
    }



    @Test
    public void delete() throws JAXBException, PersistException {
        List<Task> list = service.getAll();


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
