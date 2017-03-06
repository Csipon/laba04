package database;

import com.kurachenko.entity.Employee;
import com.kurachenko.entity.ProjectManager;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoimpl.DepartmentService;
import com.kurachenko.service.daoimpl.EmployeeService;
import com.kurachenko.service.daoimpl.ProjectManagerService;
import com.kurachenko.service.daoimpl.ProjectService;
import com.kurachenko.service.daoimpl.factory.OracleDaoFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Kurachenko
 * @since 12/26/2016
 */
public class TestProjectManager {
    private static OracleDaoFactory factory;
    private static ProjectManagerService service;
    private static DepartmentService departmentService;
    private static EmployeeService employeeService;
    private static ProjectService projectService;

    @BeforeClass
    public static void setUpBeforeClass() throws PersistException {
        factory = new OracleDaoFactory();
        service = new ProjectManagerService(factory, factory.getContext());
        employeeService = new EmployeeService(factory, factory.getContext());
        departmentService = new DepartmentService(factory, factory.getContext());
        projectService = new ProjectService(factory, factory.getContext());
    }


    @Test
    public void create() throws PersistException {
        ProjectManager projectManager = service.create();
        System.out.println(projectManager);
    }


    @Test
    public void delete() throws PersistException {
        ProjectManager manager = service.create();

        ProjectManager manager2 = service.getByPK(manager.getId());
        System.out.println(manager2);
        service.delete(manager);
    }


    @Test
    public void update() throws PersistException {
        ProjectManager manager = service.getByPK(93);
        manager.setFirstName("Piter");
        manager.setLastName("Parker");
        manager.setDepartment(departmentService.getByPK(77));

        Employee employee1 = employeeService.getByPK(65);
        Employee employee2 = employeeService.create();
        Employee[] employees = {employee1, employee2};
        manager.setSubordinates(employees);

        service.update(manager);

        manager = service.getByPK(93);
        System.out.println(manager);
    }

    @Test
    public void setParamManger() throws PersistException {
        ProjectManager manager = service.getByPK(186);

        List<Integer> list = listId(manager.getSubordinates());

        System.out.println(list);

        for (Employee e : employeeService.getAll()){
            if (!list.contains(e.getId())){
                System.out.println(e);
            }
        }

    }

    private List<Integer> listId(Employee[] employees){
        List<Integer> list = new ArrayList<>();
        for (Employee e : employees){
            list.add(e.getId());
        }
        return list;
    }

    @Test
    public void test() throws PersistException {
        ProjectManager manager = service.getByPK(93);
        System.out.println(manager);
        service.update(manager);
    }

}
