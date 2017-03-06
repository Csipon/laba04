package database;

import com.kurachenko.entity.Employee;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.daohelpers.EmployeeHelper;
import com.kurachenko.service.daoimpl.EmployeeService;
import com.kurachenko.service.daoimpl.factory.OracleDaoFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 12/21/2016
 */
public class TestEmployee {
    private static EmployeeService service;

    @BeforeClass
    public static void setUpBeforeClass() throws PersistException {
        OracleDaoFactory factory = new OracleDaoFactory();
        service = new EmployeeService(factory, factory.getContext());
    }


    @Test
    public void create() throws PersistException {
        Employee employee = service.create();
        System.out.println(employee);
        service.delete(employee);
    }

    @Test
    public void getByID() throws ParseException, PersistException {
        Employee employee = service.getByPK(65);
        System.out.println(employee);
    }

    @Test
    public void delete() throws ParseException, PersistException {
        Employee employee = service.create();
        System.out.println(employee);
        service.delete(employee);
    }


    @Test
    public void update() throws ParseException, PersistException {
        Employee employee = service.create();
        System.out.println(employee);
        employee.setFirstName("Pasha");

        System.out.println(" = = = = = = = = = = = = = = = = = = = = ");
        service.update(employee);

        Employee temp = service.getByPK(employee.getId());
        System.out.println(temp);

        service.delete(temp);
    }


    @Test
    public void testLoadFullEmployee() throws PersistException {

        Employee employee = service.getByPK(98);
        employee.setFirstName("Garet");
        employee.setLastName("Andreson");
        employee.setDescription("Line manager");
        service.update(employee);

        System.out.println("EMPLOYEE is UPDATE SUCCESSFUL");
    }


    @Test
    public void getByPK() throws PersistException {
        for (int i = 99; i < 107; i++) {

            Employee employee = service.getByPK(i);

            System.out.println(employee);
            service.delete(employee);
        }
    }

    @Test
    public void getAll() throws PersistException {

        List<Employee> employees = EmployeeHelper.getFreeEmps(service.getAll(), 179);


        for (Employee employee : employees){
            System.out.println(employee);
            System.out.println(Arrays.toString(employee.getProjects()));
        }
    }


    @Test
    public void loadProxy() throws PersistException {
        Employee employee = service.getByPK(65);

        System.out.println(employee.getName());
        System.out.println(employee.getSurname());
        System.out.println(Arrays.toString(employee.getProjects()));
        System.out.println(Arrays.toString(employee.getProjects()));
        System.out.println(employee.getDepartment());
        System.out.println(employee.getDepartment());

    }
}
