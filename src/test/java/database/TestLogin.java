package database;

import com.kurachenko.service.daoimpl.factory.OracleDaoFactory;
import org.junit.Test;

import java.io.File;

/**
 * @author Pavel Kurachenko
 * @since 1/5/2017
 */
public class TestLogin {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:oxeygen";
    private static final String NAME = "SYSTEM";
    private static final String PASSWORD = "wsgf1996";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
//    private PooledConnection pooledConnection = new OraclePooledConnection(URL, NAME, PASSWORD);

    OracleDaoFactory factory = new OracleDaoFactory();



    @Test
    public void string(){
//        System.setProperty("java.security.auth.login.config", "resources\\jaas.config");
        File file  = new File("jaas.config");

        System.out.println(file.getName());
//        for (String s : System.getProperties().stringPropertyNames()){
//            System.out.println(s + " = " + System.getProperty(s));
//        }
    }



}
