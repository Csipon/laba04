package database;

import com.kurachenko.entity.Journal;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.JournalService;
import com.kurachenko.service.daoimpl.factory.OracleDaoFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pavel Kurachenko
 * @since 1/25/2017
 */
public class TestJournal {

    private static OracleDaoFactory factory;
    private static JournalService service;

    @BeforeClass
    public static void setUpClass() throws PersistException {
        factory = new OracleDaoFactory();
        service = new JournalService(factory, factory.getContext());
    }

    @Test
    public void create() throws PersistException {
        Journal journal = service.create();

        System.out.println(journal);
    }

    @Test
    public void update() throws PersistException {
        Journal journal = service.getByPK(169);

        System.out.println(journal);
        Map<Integer, Boolean> map;
        if (journal.getMapEmployee() != null) {
            map = journal.getMapEmployee();
        }else {
            map = new HashMap<>();
        }

        map.put(132, false);
        map.put(131, true);
        map.put(133, false);
        map.put(134, true);

        journal.setMapEmployee(map);

        service.update(journal);

    }


    @Test
    public void getPK() throws PersistException {
        Journal journal = service.getByPK(169);

        System.out.println(journal);

        System.out.println(journal.getMapEmployee());
    }
}
