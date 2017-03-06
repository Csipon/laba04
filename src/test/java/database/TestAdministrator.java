package database;

import com.kurachenko.entity.Administrator;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.AdministratorService;
import com.kurachenko.service.daoimpl.factory.OracleDaoFactory;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Pavel Kurachenko
 * @since 1/19/2017
 */
public class TestAdministrator {

    private static OracleDaoFactory factory;
    private static AdministratorService service;

    @BeforeClass
    public static void setUpClass() throws PersistException {
        factory = new OracleDaoFactory();
        service = new AdministratorService(factory, factory.getContext());
    }


    @Test
    public void create() throws PersistException {
        Administrator admin = service.create();

        System.out.println(admin);

        admin.setDescription("Main admin");
        admin.setName("pasha");
        admin.setSurname("kurachenko");
        admin.setLogin("pashka");
        admin.setPassword("pashka123");

        service.update(admin);
    }
}
