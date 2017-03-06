package binary;

import com.kurachenko.entity.Customer;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.serialize.CustomerBinaryService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 1/11/2017
 */
public class TestBinaryCustomer {
    private static CustomerBinaryService service;


    @BeforeClass
    public static void setUpClass() throws PersistException {
        service = new CustomerBinaryService();
    }

    @Test
    public void save() throws PersistException {
//        for (int i = 1; i < 100; i++) {
            Customer customer = service.create();
//            if (i % 2 == 0) {
//                customer.setCompanyName("Porsche");
//            }else {
                customer.setCompanyName("Ferrari");
//            }
            customer.setDescription("Company is make sport car");
            customer.setName("Mark");
            customer.setSurname("Pork");
            service.persist(customer);
//        }
        service.save();
    }


    @Test
    public void update() throws PersistException {
        List<Customer> list = service.getAll();
        for (int i = 1; i < list.size(); i++) {
            if (i % 7 == 0){
                Customer customer = list.get(i);
                customer.setCompanyName("NOTHINK");
                service.update(customer);
            }
        }
        service.save();
    }


    @Test
    public void getAll() throws PersistException {
        Integer count = 0;
        for (Customer customer : service.getAll()){
            count++;
            System.out.println(customer);
        }
        System.out.println(count);
    }
}
