package xml;

import com.kurachenko.entity.Journal;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.xml.JournalXmlService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.Date;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 2/21/2017
 */
public class TestXmlJournal {
    private static JournalXmlService service;


    @BeforeClass
    public static void setUpClass(){
        service = new JournalXmlService();
    }

    @Test
    public void create() throws JAXBException, PersistException {
        for (int i = 0; i < 10; i++) {
            Journal o = service.create();
            o.setIdSprint(i);
            service.persist(o);
        }
        service.save();
        for (Journal e : service.getAll()){
            System.out.println(e);
        }
    }


    @Test
    public void getAll() throws PersistException {
        for (Journal e : service.getAll()){
            System.out.println(e);
        }
    }


    @Test
    public void update() throws PersistException {
        for (Journal e : service.getAll()){
            e.setStartTask(new Date());
            System.out.println(e);
            service.update(e);
        }

        service.save();
    }



    @Test
    public void delete() throws JAXBException, PersistException {
        List<Journal> list = service.getAll();


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
