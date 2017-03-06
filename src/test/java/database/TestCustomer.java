package database;

import com.kurachenko.entity.Customer;
import com.kurachenko.entity.Project;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.CustomerService;
import com.kurachenko.service.daoimpl.factory.OracleDaoFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Pavel Kurachenko
 * @since 1/4/2017
 */
public class TestCustomer {
    private static CustomerService service;

    @BeforeClass
    public static void setUpClass() throws PersistException {
        OracleDaoFactory factory = new OracleDaoFactory();
        service = new CustomerService(factory, factory.getContext());
    }


    @Test
    public void create() throws PersistException {
        Customer customer = service.create();
        System.out.println(customer);
    }

    @Test
    public void delete() throws PersistException {
        Customer customer = service.create();
        System.out.println(customer);
        service.delete(customer);
    }

    @Test
    public void getByPK() throws PersistException {
        Customer customer = service.getByPK(125);
        List<Project> list = service.getCustomerProject(customer.getId());

        for (Project project : list){
            System.out.println(project);
        }

    }


    @Test
    public void update() throws PersistException {
        Customer customer = service.create();
        System.out.println(customer);
        customer.setName("Phil");
        service.update(customer);
        Customer customer1 = service.getByPK(customer.getId());

        assertTrue(customer.getId() == customer1.getId());
        assertEquals("Phil", customer1.getName());
        service.delete(customer);
    }





    @Test
    public void setCustomerParam() throws PersistException {
        Customer customer = service.getByPK(95);
        customer.setDescription("Application for android system");
        customer.setName("Phil");
        customer.setSurname("Khalen");
        customer.setCompanyName("ANDROID Inc. corporation");

        service.update(customer);
    }



}
