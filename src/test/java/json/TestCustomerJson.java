package json;

import com.kurachenko.entity.Customer;
import com.kurachenko.exception.PersistException;
import com.kurachenko.file.json.CustomerFileService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 1/10/2017
 */
public class TestCustomerJson {
    private static CustomerFileService service;

    @BeforeClass
    public static void setUpClass() throws IOException, PersistException {
        service = new CustomerFileService();
    }

    @Test
    public void create() throws PersistException {
        Customer customer = service.create();
        System.out.println(customer);
    }


    @Test
    public void save() throws PersistException {
        Customer customer = service.create();
        customer.setName("Alex");
        customer.setSurname("Baurman");
        service.persist(customer);
    }

    @Test
    public void read() throws PersistException {
        List<Customer> list = service.getAll();
        for (Customer c : list) {
            System.out.println(c);
        }
    }


    @Test
    public void update() throws PersistException {
        Customer customer = service.getByPK(3);
        System.out.println(customer);
        customer.setCompanyName("New York Times");
        service.update(customer);
    }

    @Test
    public void delete() throws PersistException {
        Customer customer = service.getByPK(2);
        System.out.println(customer);
        service.delete(customer);
    }
}
