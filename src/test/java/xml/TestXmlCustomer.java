package xml;

import com.kurachenko.entity.Customer;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.xml.CustomerXmlService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 2/21/2017
 */
public class TestXmlCustomer {

    private static CustomerXmlService service;


    @BeforeClass
    public static void setUpClass(){
        service = new CustomerXmlService();
    }

    @Test
    public void create() throws JAXBException, PersistException {
        for (int i = 0; i < 10; i++) {
            Customer obj = service.create();
            obj.setName("pasha = " + i);
            service.persist(obj);
        }
        service.save();
        for (Customer e : service.getAll()){
            System.out.println(e);
        }
    }


    @Test
    public void getAll() throws PersistException {
        for (Customer e : service.getAll()){
            System.out.println(e);
        }
    }


    @Test
    public void update() throws PersistException {
        for (Customer e : service.getAll()){
            e.setLogin("123123123");
            System.out.println(e);
            service.update(e);
        }

        service.save();
    }



    @Test
    public void delete() throws JAXBException, PersistException {
        List<Customer> list = service.getAll();


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
