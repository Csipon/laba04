package database;

import com.kurachenko.service.daoimpl.StoreConstantForDB;
import com.kurachenko.service.daoimpl.factory.OracleDaoFactory;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pavel Kurachenko
 * @since 1/5/2017
 */
public class TestLogin {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:oxeygen";
    private static final String NAME = "SYSTEM";
    private static final String PASSWORD = "wsgf1996";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

    OracleDaoFactory factory = new OracleDaoFactory();



    @Test
    public void string() throws SQLException {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(DRIVER);
        driverManagerDataSource.setUrl(URL);
        driverManagerDataSource.setUsername(NAME);
        driverManagerDataSource.setPassword(PASSWORD);

        Connection connection = driverManagerDataSource.getConnection();


        PreparedStatement statement = connection.prepareStatement(StoreConstantForDB.ALL_PASSWORDS);
        ResultSet resultSet = statement.executeQuery();
        Map<Integer, String> map = new HashMap<>();
        while (resultSet.next()){
            Integer id = resultSet.getInt("object_id");
            String value = resultSet.getString("value");
            map.put(id, value);
        }


//        for (Map.Entry<Integer, String> m : map.entrySet()){
//            map.put(m.getKey(), CheckHelper.passwordDecode(m.getValue()));
//        }

        for (Map.Entry<Integer, String> m : map.entrySet()){
            System.out.println(m.getKey() + " = " + m.getValue());
        }

//        PreparedStatement statement1 = connection.prepareStatement(StoreConstantForDB.UPDATE_PARAMS);
//        for (Map.Entry<Integer, String> m : map.entrySet()){
//            statement1.setString(1, "123");
//            statement1.setInt(2, m.getKey());
//            statement1.setString(3, "password");
//            int i = statement1.executeUpdate();
//            System.out.println(i);
//        }
    }



}
