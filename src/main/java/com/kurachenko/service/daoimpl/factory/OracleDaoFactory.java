package com.kurachenko.service.daoimpl.factory;

import com.kurachenko.entity.*;
import com.kurachenko.exception.PersistException;
import com.kurachenko.service.daoabstract.interfaces.DaoFactory;
import com.kurachenko.service.daoabstract.interfaces.GenericDao;
import com.kurachenko.service.daoimpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pavel Kurachenko
 * @since 12/21/2016
 */
@Repository
public class OracleDaoFactory implements DaoFactory<Connection> {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:oxeygen";
    private static final String NAME = "SYSTEM";
    private static final String PASSWORD = "wsgf1996";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private Map<Class, DaoCreator<Connection>> cacheService;
    @Autowired
    private DataSource dataSource;


    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(DRIVER);
        driverManagerDataSource.setUrl(URL);
        driverManagerDataSource.setUsername(NAME);
        driverManagerDataSource.setPassword(PASSWORD);
        return driverManagerDataSource;
    }


    @Bean
    @Override
    public Connection getContext() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator<Connection> creator = cacheService.get(dtoClass);
        if (creator == null){
            throw new PersistException("This Dao creator is not defined");
        }
        return creator.create(connection);
    }


    public OracleDaoFactory() {

        cacheService = new HashMap<>();

        cacheService.put(Employee.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) throws PersistException {
                return new EmployeeService(OracleDaoFactory.this, connection);
            }
        });
        cacheService.put(Project.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) throws PersistException {
                return new ProjectService(OracleDaoFactory.this, connection);
            }
        });
        cacheService.put(Department.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) throws PersistException {
                return new DepartmentService(OracleDaoFactory.this, connection);
            }
        });
        cacheService.put(ProjectManager.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) throws PersistException {
                return new ProjectManagerService(OracleDaoFactory.this, connection);
            }
        });
        cacheService.put(Customer.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) throws PersistException {
                return new CustomerService(OracleDaoFactory.this, connection);
            }
        });
        cacheService.put(Task.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) throws PersistException {
                return new TaskService(OracleDaoFactory.this, connection);
            }
        });
        cacheService.put(Sprint.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) throws PersistException {
                return new SprintService(OracleDaoFactory.this, connection);
            }
        });
        cacheService.put(Journal.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) throws PersistException {
                return new JournalService(OracleDaoFactory.this, connection);
            }
        });
        cacheService.put(Message.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) throws PersistException {
                return new MessageService(OracleDaoFactory.this, connection);
            }
        });
    }
}
